
package okgo.request;

import okgo.model.HttpMethod;
import okgo.request.base.NoBodyRequest;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2016/1/12
 * 描    述：Trace请求的实现类，注意需要传入本类的泛型
 * 修订历史：
 * ================================================
 */
public class TraceRequest<T> extends NoBodyRequest<T, TraceRequest<T>> {

    public TraceRequest(String url) {
        super(url);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.TRACE;
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        Request.Builder requestBuilder = generateRequestBuilder(requestBody);
        return requestBuilder.method("TRACE", requestBody).url(url).tag(tag).build();
    }
}
