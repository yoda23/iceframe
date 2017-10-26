package com.edt.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface RedisService {
	/**
     *
	 * 获取给定集合中所有Key值对应的Value
	 * 
	 * @param collection
	 *            collection
	 * @return java.util.List<java.lang.Object>
	 * @author 刘钢 2017-07-21 14:47
	 */

	List<Object> opsValue_MultiGet(Collection<String> collection);

	/**
	 * 添加数据-为多个键分别设置它们的值，如果存在则返回false，不存在返回true
	 *
	 * @param map
	 *            map
	 * @author 刘钢 2017-07-21 14:35
	 */

	Boolean opsValue_MultiSetIfAbsent(Map<String, Object> map);

	/**
	 * 添加数据-为多个键分别设置它们的值
	 *
	 * @param map
	 *            map
	 * @author 刘钢 2017-07-21 14:35
	 */

	void opsValue_MultiSet(Map<String, Object> map);

	/**
	 * 获取key的时间，并将其转换为给定的时间单元
	 *
	 * @param key
	 *            key
	 * @param timeUnit
	 *            timeUnit
	 * @return Long
	 * @author 刘钢 2017/6/5 22:06
	 */
	Long getExpire(String key, TimeUnit timeUnit);

	/**
	 * 获取key存活的时间，如果没有设置存活时间，返回-1
	 *
	 * @param key
	 *            key
	 * @return Long
	 * @author 刘钢 2017/6/5 22:04
	 */
	Long getExpire(String key);

	/**
	 * 给Key值设置有效时间
	 *
	 * @param key
	 *            key
	 * @param date
	 *            date
	 * @return boolean
	 * @author 刘钢 2017/6/5 21:48
	 */
	boolean expire(String key, Date date);

	/**
	 * 给Key值设置有效时间
	 *
	 * @param key
	 *            key
	 * @param timeUnit
	 *            timeUnit
	 * @param timeOut
	 *            timeOut
	 * @return boolean
	 * @author 刘钢 2017/5/21 12:58
	 */
	boolean expire(String key, Long timeOut, TimeUnit timeUnit);

	/**
	 * 添加数据
	 *
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @author 刘钢 2017/5/21 12:49
	 */
	void opsValue_set(String key, Object value);

	/**
	 * 添加数据-从偏移量 long1 开始
	 *
	 * @param key
	 *            key
	 * @param long1
	 *            long1
	 * @param value
	 *            value
	 * @author 刘钢 2017/5/21 12:49
	 */
	void opsValue_set(String key, Object value, long long1);

	/**
	 * 添加数据-带有效期
	 *
	 * @param key
	 *            key
	 * @param timeOut
	 *            timeOut
	 * @param timeUnit
	 *            timeUnit
	 * @param value
	 *            value
	 * @author 刘钢 2017/5/21 12:50
	 */
	void opsValue_set(String key, Object value, long timeOut,
			TimeUnit timeUnit);

	/**
	 * 查询数据
	 *
	 * @param key
	 *            key
	 * @param elementType
	 *            elementType
	 * @return T
	 * @author 刘钢 2017/5/21 12:55
	 */
	<T> T opsValue_get(String key, Class<T> elementType);

	/**
	 * 查询数据，从long1到long2位置的value内容
	 *
	 * @param key
	 *            key
	 * @param long1
	 *            long1
	 * @param long2
	 *            long2
	 * @param elementType
	 *            elementType
	 * @return T
	 * @author 刘钢 2017/5/21 12:55
	 */
	<T> T opsValue_get(String key, long long1, long long2,
			Class<T> elementType);

	/**
	 * 删除数据
	 *
	 * @param key
	 *            key
	 * @author 刘钢 2017/5/21 12:56
	 */
	void opsValue_delete(String key);

	/**
	 * 删除数据
	 *
	 * @param collection
	 *            collection
	 * @author 刘钢 2017/5/21 12:56
	 */
	void opsValue_delete(Collection<String> collection);

	/**
	 * 如果"key"存在返回false；如果"key"不存在，就存入reidis返回true
	 *
	 * @param key
	 *            key
	 * @param object
	 *            object
	 * @return java.lang.Boolean
	 * @author 刘钢 2017/5/21 12:56
	 */
	Boolean opsValue_setIfAbsent(String key, Object object);

	/**
	 * 如果"key"存在返回false；如果"key"不存在，就存入reidis返回true
	 *
	 * @param key
	 *            key
	 * @param object
	 *            object
	 * @param timeOut
	 *            timeOut
	 * @param timeUnit
	 *            timeUnit
	 * @return java.lang.Boolean
	 * @author 刘钢 2017/5/21 12:56
	 */
	Boolean opsValue_setIfAbsent(String key, Object object, Long timeOut,
			TimeUnit timeUnit);

	/**
	 * 如果有"key"存在，返回value，并且将value赋值为新的值
	 *
	 * @param key
	 *            key
	 * @param elementType
	 *            elementType
	 * @return T
	 * @author 刘钢 2017/5/21 12:57
	 */
	<T> T opsValue_getAndSet(String key, Class<T> elementType, Object object);

	/**
	 * 将key的值增加value
	 *
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 12:57
	 */
	Long opsValue_increment(String key, long value);

	/**
	 * 将key的值增加value
	 *
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @return java.lang.Double
	 * @author 刘钢 2017/5/21 12:57
	 */
	Double opsValue_increment(String key, double value);
	// -------------------队列操作--------------------------------------

	/**
	 * 返回队列long1到long2之间的内容
	 *
	 * @param <T>
	 *            T
	 * @param key
	 *            key
	 * @param elementType
	 *            elementType
	 * @param long1
	 *            long1
	 * @param long2
	 *            long2
	 * @return java.util.List<T>
	 * @author 刘钢 2017/5/21 12:57
	 */
	<T> List<T> opsList_range(String key, Class<T> elementType, long long1,
			long long2);

	/**
	 * 删除队列数据，除了[long1,long2]以外的所有元素
	 *
	 * @param key
	 *            key
	 * @param long1
	 *            long1
	 * @param long2
	 *            long2
	 * @author 刘钢 2017/5/21 12:58
	 */
	void opsList_trim(String key, long long1, long long2);

	/**
	 * 返回队列长度
	 *
	 * @param key
	 *            key
	 * @return long
	 * @author 刘钢 2017/5/21 12:58
	 */
	long opsList_size(String key);

	/**
	 * 从左边入队
	 *
	 * @param key
	 *            key
	 * @param object
	 *            object
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 12:58
	 */
	Long opsList_leftPush(String key, Object object);

	/**
	 * 从左边入队
	 *
	 * @param key
	 *            key
	 * @param object1
	 *            object1
	 * @param object2
	 *            object2
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 12:59
	 */
	Long opsList_leftPush(String key, Object object1, Object object2);

	/**
	 * 从左边入队-带有时效
	 *
	 * @param key
	 *            key
	 * @param object
	 *            object
	 * @param timeOut
	 *            timeOut
	 * @param timeUnit
	 *            timeUnit
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 13:00
	 */
	Long opsList_leftPush(String key, Object object, Long timeOut,
			TimeUnit timeUnit);

	/**
	 * 从左边入队，将参数全部入队
	 *
	 * @param key
	 *            key
	 * @param object
	 *            object
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 13:01
	 */
	Long opsList_leftPushAll(String key, Object... object);

	/**
	 * 从左边入队，将参数全部入队
	 *
	 * @param key
	 *            key
	 * @param collection
	 *            collection
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 13:01
	 */
	Long opsList_leftPushAll(String key, Collection<Object> collection);

	/**
	 * 如果key存在，放入value中，否则不操作，返回0
	 *
	 * @param key
	 *            key
	 * @param object
	 *            object
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 13:49
	 */
	Long opsList_leftPushIfPresent(String key, Object object);

	/**
	 * 右边入队
	 *
	 * @param key
	 *            key
	 * @param object
	 *            object
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 13:49
	 */
	Long opsList_rightPush(String key, Object object);

	/**
	 * 右边入队-带有时效
	 *
	 * @param key
	 *            key
	 * @param object
	 *            object
	 * @param timeOut
	 *            timeOut
	 * @param timeUnit
	 *            timeUnit
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 13:49
	 */
	Long opsList_rightPush(String key, Object object, Long timeOut,
			TimeUnit timeUnit);

	/**
	 * 从右边边入队，将参数全部入队
	 *
	 * @param key
	 *            key
	 * @param object
	 *            object
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 13:50
	 */
	Long opsList_rightPushAll(String key, Object... object);

	/**
	 * 从右边入队，将参数全部入队
	 *
	 * @param key
	 *            key
	 * @param collection
	 *            collection
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 13:50
	 */
	Long opsList_rightPushAll(String key, Collection<Object> collection);

	/**
	 * 如果key存在，放入value中，否则不操作，返回0
	 *
	 * @param key
	 *            key
	 * @param object
	 *            object
	 * @return java.lang.Long
	 * @author 刘钢 2017/5/21 13:50
	 */
	Long opsList_rightPushIfPresent(String key, Object object);

	/**
	 * 更新已有的元素
	 *
	 * @param key
	 *            key
	 * @param long1
	 *            long1
	 * @param object
	 *            object
	 * @return void
	 * @author 刘钢 2017/5/21 13:51
	 */
	void opsList_set(String key, long long1, Object object);

	/**
	 * 删除N个元素值为“XXXX”，返回删除的个数；如果没有这个元素则返回0
	 *
	 * @param index
	 *            index
	 * @param key
	 *            key
	 * @param object
	 *            object
	 * @return long
	 * @author 刘钢 2017/5/21 13:51
	 */
	long opsList_remove(String key, long index, Object object);

	/**
	 * 列出index行的值，从0开始
	 *
	 * @param key
	 *            key
	 * @param index
	 *            index
	 * @param elementType
	 *            elementType
	 * @return T
	 * @author 刘钢 2017/5/21 13:51
	 */
	<T> T opsList_index(String key, long index, Class<T> elementType);

	/**
	 * 将源key的队列的右边的一个值删除，然后塞入目标key的队列的左边，返回这个值
	 *
	 * @Title rightPopAndLeftPush
	 * @param from
	 * @param to
	 * @param elementType
	 * @return
	 * @throws @author
	 *             刘钢
	 * @Date 2017年4月19日 上午9:50:08
	 */
	/**
	 * 左边出队，返回出对内容
	 *
	 * @param key
	 *            key
	 * @param elementType
	 *            elementType
	 * @return T
	 * @author 刘钢 2017/5/21 13:51
	 */
	<T> T opsList_leftPop(String key, Class<T> elementType);

	/**
	 * @param key
	 *            key
	 * @param elementType
	 *            elementType
	 * @param long1
	 *            long1
	 * @param timeUnit
	 *            timeUnit
	 * @return T
	 * @author 刘钢 2017/5/21 13:52
	 */
	<T> T opsList_leftPop(String key, Class<T> elementType, long long1,
			TimeUnit timeUnit);

	/**
	 * @param key
	 *            key
	 * @param elementType
	 *            elementType
	 * @return T
	 * @author 刘钢 2017/5/21 13:52
	 */
	<T> T opsList_rightPop(String key, Class<T> elementType);

	/**
	 * @param key
	 *            key
	 * @param elementType
	 *            elementType
	 * @param long1
	 *            long1
	 * @param timeUnit
	 *            timeUnit
	 * @return T
	 * @author 刘钢 2017/5/21 13:52
	 */
	<T> T opsList_rightPop(String key, Class<T> elementType, long long1,
			TimeUnit timeUnit);

	/**
	 * @param from
	 *            from
	 * @param to
	 *            to
	 * @param elementType
	 *            elementType
	 * @return T
	 * @author 刘钢 2017/5/21 13:53
	 */
	<T> T opsList_rightPopAndLeftPush(String from, String to,
			Class<T> elementType);

	/**
	 * long long1, TimeUnit timeUnit
	 *
	 * @param from
	 *            from
	 * @param to
	 *            to
	 * @param elementType
	 *            elementType
	 * @param long1
	 *            long1
	 * @param timeUnit
	 *            timeUnit
	 * @return T
	 * @author 刘钢 2017/5/21 13:53
	 */
	<T> T opsList_rightPopAndLeftPush(String from, String to,
			Class<T> elementType, long long1, TimeUnit timeUnit);

}
