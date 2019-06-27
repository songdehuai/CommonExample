package okgo.callback

import okgo.cache.CacheMode
import okgo.convert.Converter
import okgo.model.Progress
import okgo.model.Response
import okgo.request.base.Request

/**
 * ================================================
 * 作    者：
 * 版   本：1.0
 * 创建日期：2019/6/27
 * 描   述：抽象的回调接口
 * 修订历史：
 * ================================================
 *
 * 该类的回调具有如下顺序,虽然顺序写的很复杂,但是理解后,是很简单,并且合情合理的
 *
 * 1.无缓存模式[CacheMode.NO_CACHE]<br></br>
 * ---网络请求成功 onStart -> convertResponse -> onSuccess -> onFinish<br></br>
 * ---网络请求失败 onStart -> onError -> onFinish<br></br>
 *
 * 2.默认缓存模式,遵循304头[CacheMode.DEFAULT]<br></br>
 * ---网络请求成功,服务端返回非304 onStart -> convertResponse -> onSuccess -> onFinish<br></br>
 * ---网络请求成功服务端返回304 onStart -> onCacheSuccess -> onFinish<br></br>
 * ---网络请求失败 onStart -> onError -> onFinish<br></br>
 *
 * 3.请求网络失败后读取缓存[CacheMode.REQUEST_FAILED_READ_CACHE]<br></br>
 * ---网络请求成功,不读取缓存 onStart -> convertResponse -> onSuccess -> onFinish<br></br>
 * ---网络请求失败,读取缓存成功 onStart -> onCacheSuccess -> onFinish<br></br>
 * ---网络请求失败,读取缓存失败 onStart -> onError -> onFinish<br></br>
 *
 * 4.如果缓存不存在才请求网络，否则使用缓存[CacheMode.IF_NONE_CACHE_REQUEST]<br></br>
 * ---已经有缓存,不请求网络 onStart -> onCacheSuccess -> onFinish<br></br>
 * ---没有缓存请求网络成功 onStart -> convertResponse -> onSuccess -> onFinish<br></br>
 * ---没有缓存请求网络失败 onStart -> onError -> onFinish<br></br>
 *
 * 5.先使用缓存，不管是否存在，仍然请求网络[CacheMode.FIRST_CACHE_THEN_REQUEST]<br></br>
 * ---无缓存时,网络请求成功 onStart -> convertResponse -> onSuccess -> onFinish<br></br>
 * ---无缓存时,网络请求失败 onStart -> onError -> onFinish<br></br>
 * ---有缓存时,网络请求成功 onStart -> onCacheSuccess -> convertResponse -> onSuccess -> onFinish<br></br>
 * ---有缓存时,网络请求失败 onStart -> onCacheSuccess -> onError -> onFinish<br></br>
 */
interface Callback<T> : Converter<T> {

    /**
     * 请求网络开始前，UI线程
     */
    fun onStart(request: Request<T, out Request<*, *>>)

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    fun onSuccess(response: Response<T>)

    /**
     * 缓存成功的回调,UI线程
     */
    fun onCacheSuccess(response: Response<T>)

    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     */
    fun onError(response: Response<T>)

    /**
     * 请求网络结束后，UI线程
     */
    fun onFinish()

    /**
     * 上传过程中的进度回调，get请求不回调，UI线程
     */
    fun uploadProgress(progress: Progress)

    /**
     * 下载过程中的进度回调，UI线程
     */
    fun downloadProgress(progress: Progress)
}
