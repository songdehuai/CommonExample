
package okgo.callback

import okgo.model.Progress
import okgo.model.Response
import okgo.request.base.Request
import okgo.utils.OkLogger

/**
 * ================================================
 * 作    者：
 * 版   本：1.0
 * 创建日期：2016/1/14
 * 描   述：抽象的回调接口
 * 修订历史：
 * ================================================
 */
abstract class AbsCallback<T> : Callback<T> {

    override fun onStart(request: Request<T, out Request<*, *>>) {}

    override fun onCacheSuccess(response: Response<T>) {}

    override fun onSuccess(response: Response<T>) {

    }

    override fun onError(response: Response<T>) {
        OkLogger.printStackTrace(response.exception)
    }

    override fun onFinish() {}

    override fun uploadProgress(progress: Progress) {}

    override fun downloadProgress(progress: Progress) {}
}
