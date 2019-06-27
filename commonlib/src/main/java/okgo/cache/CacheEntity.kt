
package okgo.cache

import android.content.ContentValues
import android.database.Cursor

import java.io.Serializable

import okgo.model.HttpHeaders
import okgo.utils.IOUtils

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：16/9/11
 * 描    述：
 * 修订历史：
 * ================================================
 */
class CacheEntity<T> : Serializable {

    var key: String? = null                    // 缓存key
    var localExpire: Long = 0              // 缓存过期时间
    var responseHeaders: HttpHeaders? = null   // 缓存的响应头
    var data: T? = null                        // 缓存的实体数据
    var isExpire: Boolean = false   //缓存是否过期该变量不必保存到数据库，程序运行起来后会动态计算

    /**
     * @param cacheTime 允许的缓存时间
     * @param baseTime  基准时间,小于当前时间视为过期
     * @return 是否过期
     */
    fun checkExpire(cacheMode: CacheMode, cacheTime: Long, baseTime: Long): Boolean {
        //304的默认缓存模式,设置缓存时间无效,需要依靠服务端的响应头控制
        if (cacheMode === CacheMode.DEFAULT) return localExpire < baseTime
        return if (cacheTime == CACHE_NEVER_EXPIRE) false else localExpire + cacheTime < baseTime
    }

    override fun toString(): String {
        return "CacheEntity{key='" + key + '\''.toString() + //

                ", responseHeaders=" + responseHeaders + //

                ", data=" + data + //

                ", localExpire=" + localExpire + //

                '}'.toString()
    }

    companion object {
        private const val serialVersionUID = -4337711009801627866L

        val CACHE_NEVER_EXPIRE: Long = -1        //缓存永不过期

        //表中的字段
        val KEY = "key"
        val LOCAL_EXPIRE = "localExpire"
        val HEAD = "head"
        val DATA = "data"

        fun <T> getContentValues(cacheEntity: CacheEntity<T>): ContentValues {
            val values = ContentValues()
            values.put(KEY, cacheEntity.key)
            values.put(LOCAL_EXPIRE, cacheEntity.localExpire)
            values.put(HEAD, IOUtils.toByteArray(cacheEntity.responseHeaders))
            values.put(DATA, IOUtils.toByteArray(cacheEntity.data))
            return values
        }

        fun <T> parseCursorToBean(cursor: Cursor): CacheEntity<T> {
            val cacheEntity = CacheEntity<T>()
            cacheEntity.key = cursor.getString(cursor.getColumnIndex(KEY))
            cacheEntity.localExpire = cursor.getLong(cursor.getColumnIndex(LOCAL_EXPIRE))
            cacheEntity.responseHeaders =
                IOUtils.toObject(cursor.getBlob(cursor.getColumnIndex(HEAD))) as HttpHeaders

            cacheEntity.data = IOUtils.toObject(cursor.getBlob(cursor.getColumnIndex(DATA))) as T
            return cacheEntity
        }
    }
}
