package com.lizikj.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

/**
 * Created by Michael.Huang on 2017/4/1.
 */
public class ObjectConvertUtil {
	private final static Logger logger = LoggerFactory.getLogger(ObjectConvertUtil.class);

	private static MapperFacade mapper;
	private static MapperFactory mapperFactory;

	static {
		mapperFactory = new DefaultMapperFactory.Builder().build();
		mapper = mapperFactory.getMapperFacade();
	}

	public static MapperFactory getMapperFactory() {
		return mapperFactory;
	}

	public static <T, E> T copyProperties(Class<T> destClassType, E orig) throws RuntimeException {
		if (orig == null) {
			logger.warn("转换对象为空");
			return null;
		}
		try {
			T dest = map(orig, destClassType);
			return dest;
		} catch (Exception e) {
			logger.error("转换对象出错:", e);
			throw new RuntimeException("对象复制异常");
		}
	}

	public static <F, T> List<T> copyListProperties(List<F> origList, final Class<T> destClassType) {
		if (origList == null) {
			logger.error("转换对象为空");
			return null;
		}
		List<T> destList = Lists.transform(origList, new Function<F, T>() {
			@Override
			public T apply(F orig) {
				return ObjectConvertUtil.copyProperties(destClassType, orig);
			}
		});
		return Lists.newArrayList(destList);
	}

	/**
	 * 简单的复制出新类型对象.
	 *
	 * 通过source.getClass() 获得源Class
	 */
	public static <S, D> D map(S source, Class<D> destinationClass) {
		return mapper.map(source, destinationClass);
	}

	/**
	 * 极致性能的复制出新类型对象.
	 *
	 * 预先通过BeanMapper.getType() 静态获取并缓存Type类型，在此处传入
	 */
	public static <S, D> D map(S source, Type<S> sourceType, Type<D> destinationType) {
		return mapper.map(source, sourceType, destinationType);
	}

	/**
	 * 简单的复制出新对象列表到ArrayList
	 *
	 * 不建议使用mapper.mapAsList(Iterable<S>,Class<D>)接口, sourceClass需要反射，实在有点慢
	 */
	public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<S> sourceClass, Class<D> destinationClass) {
		return mapper.mapAsList(sourceList, TypeFactory.valueOf(sourceClass), TypeFactory.valueOf(destinationClass));
	}

	/**
	 * 极致性能的复制出新类型对象到ArrayList.
	 *
	 * 预先通过BeanMapper.getType() 静态获取并缓存Type类型，在此处传入
	 */
	public static <S, D> List<D> mapList(Iterable<S> sourceList, Type<S> sourceType, Type<D> destinationType) {
		return mapper.mapAsList(sourceList, sourceType, destinationType);
	}

	/**
	 * 简单复制出新对象列表到数组
	 *
	 * 通过source.getComponentType() 获得源Class
	 */
	public static <S, D> D[] mapArray(final D[] destination, final S[] source, final Class<D> destinationClass) {
		return mapper.mapAsArray(destination, source, destinationClass);
	}

	/**
	 * 极致性能的复制出新类型对象到数组
	 *
	 * 预先通过BeanMapper.getType() 静态获取并缓存Type类型，在此处传入
	 */
	public static <S, D> D[] mapArray(D[] destination, S[] source, Type<S> sourceType, Type<D> destinationType) {
		return mapper.mapAsArray(destination, source, sourceType, destinationType);
	}

	/**
	 * 预先获取orika转换所需要的Type，避免每次转换.
	 */
	public static <E> Type<E> getType(final Class<E> rawType) {
		return TypeFactory.valueOf(rawType);
	}

	/**
	 * Map转object
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception Object
	 * @author lijundong
	 * @param <T>
	 * @param <T>
	 * @date 2017年9月4日 下午7:54:13
	 */
	@SuppressWarnings("unchecked")
	public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass){
		if (map == null)
			return null;

		Object obj = null;
		try {
			obj = beanClass.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				Method setter = property.getWriteMethod();
				if (setter != null) {
					setter.invoke(obj, map.get(property.getName()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (T)obj;
	}

	/**
	 * object转map
	 * @param obj
	 * @return
	 * @throws Exception Map<String,Object>
	 * @author lijundong
	 * @date 2017年9月4日 下午7:54:24
	 */
	public static Map<String, Object> objectToMap(Object obj) {
		if (obj == null)
			return null;

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (key.compareToIgnoreCase("class") == 0) {
					continue;
				}
				Method getter = property.getReadMethod();
				Object value = getter != null ? getter.invoke(obj) : null;
				map.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
