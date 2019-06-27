
package okgo.model;

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2016/1/19
 * 描    述：优先级的枚举类
 * 修订历史：
 * ================================================
 */
public interface Priority {
    int UI_TOP = Integer.MAX_VALUE;
    int UI_NORMAL = 1000;
    int UI_LOW = 100;
    int DEFAULT = 0;
    int BG_TOP = -100;
    int BG_NORMAL = -1000;
    int BG_LOW = Integer.MIN_VALUE;
}
