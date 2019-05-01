Android系统为了跟iOS不一样，当界面OverScroll的时候会显示一个阴影。为了达到更好的显示效果，最好禁用系统的overScroll，如上给RecyclerView添加android:overScrollMode="never"。
3.在Activity或者Fragment中配置
TwinklingRefreshLayout不会自动结束刷新或者加载更多，需要手动控制

refreshLayout.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                },2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                },2000);
            }
        });
    }

使用finishRefreshing()方法结束刷新，finishLoadmore()方法结束加载更多。此处OnRefreshListener还有其它方法，可以选择需要的来重写。

如果你想进入到界面的时候主动调用下刷新，可以调用startRefresh()/startLoadmore()方法。
setWaveHeight、setHeaderHeight、setBottomHeight、setOverScrollHeight

    setMaxHeadHeight 设置头部可拉伸的最大高度。
    setHeaderHeight 头部固定高度(在此高度上显示刷新状态)
    setMaxBottomHeight
    setBottomHeight 底部高度
    setOverScrollHeight 设置最大的越界高度

setEnableRefresh、setEnableLoadmore

灵活的设置是否禁用上下拉。
setHeaderView(IHeaderView headerView)、setBottomView(IBottomView bottomView)

设置头部/底部个性化刷新效果，头部需要实现IHeaderView，底部需要实现IBottomView。
setEnableOverScroll

是否允许越界回弹。
setOverScrollTopShow、setOverScrollBottomShow、setOverScrollRefreshShow

是否允许在越界的时候显示刷新控件，默认是允许的，也就是Fling越界的时候Header或Footer照常显示，反之就是不显示；可能有特殊的情况，刷新控件会影响显示体验才设立了这个状态。
setPureScrollModeOn()

开启纯净的越界回弹模式，也就是所有刷新相关的View都不显示，只显示越界回弹效果
setAutoLoadMore

是否在底部越界的时候自动切换到加载更多模式
addFixedExHeader

添加一个固定在顶部的Header(效果还需要优化)
startRefresh、startLoadMore、finishRefreshing、finishLoadmore
setFloatRefresh(boolean)

支持切换到像SwipeRefreshLayout一样的悬浮刷新模式了。
setTargetView(View view)

设置滚动事件的作用对象。
setDefaultHeader、setDefaultFooter

现在已经提供了设置默认的Header、Footer的static方法，可在Application或者一个Activity中这样设置：

TwinklingRefreshLayout.setDefaultHeader(SinaRefreshView.class.getName());
TwinklingRefreshLayout.setDefaultFooter(BallPulseView.class.getName());

4.扩展属性

    tr_max_head_height 头部拉伸允许的最大高度
    tr_head_height 头部高度
    tr_max_bottom_height
    tr_bottom_height 底部高度
    tr_overscroll_height 允许越界的最大高度
    tr_enable_refresh 是否允许刷新,默认为true
    tr_enable_loadmore 是否允许加载更多,默认为true
    tr_pureScrollMode_on 是否开启纯净的越界回弹模式
    tr_overscroll_top_show - 否允许顶部越界时显示顶部View
    tr_overscroll_bottom_show 是否允许底部越界时显示底部View
    tr_enable_overscroll 是否允许越界回弹
    tr_floatRefresh 开启悬浮刷新模式
    tr_autoLoadMore 越界时自动加载更多
    tr_enable_keepIView 是否在开始刷新之后保持状态，默认为true；若需要保持原来的操作逻辑，这里设置为false即可
    tr_showRefreshingWhenOverScroll 越界时直接显示正在刷新中的头部
    tr_showLoadingWhenOverScroll 越界时直接显示正在加载更多中的底部

其它说明
1.默认支持越界回弹，并可以随手势越界不同的高度

这一点很多类似SwipeRefreshLayout的刷新控件都没有做到(包括SwipeRefreshLayout),因为没有拦截下来的时间会传递给列表控件，而列表控件的滚动状态很难获取。解决方案就是给列表控件设置了OnTouchListener并把事件交给GestureDetector处理,然后在列表控件的OnScrollListener中监听View是否滚动到了顶部(没有OnScrollListener的则采用延时监听策略)。
2.setOnRefreshListener大量可以回调的方法

    onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) 正在下拉的过程
    onPullingUp(TwinklingRefreshLayout refreshLayout, float fraction) 正在上拉的过程
    onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) 下拉释放过程
    onPullUpReleasing(TwinklingRefreshLayout refreshLayout, float fraction) 上拉释放过程
    onRefresh(TwinklingRefreshLayout refreshLayout) 正在刷新
    onLoadMore(TwinklingRefreshLayout refreshLayout) 正在加载更多

其中fraction表示当前下拉的距离与Header高度的比值(或者当前上拉距离与Footer高度的比值)。
3.Header和Footer
BezierLayout(pic 4)

    setWaveColor
    setRippleColor

GoogleDotView(pic 5)
SinaRefreshView(pic 3)

    setArrowResource
    setTextColor
    setPullDownStr
    setReleaseRefreshStr
    setRefreshingStr

ProgressLayout(SwipeRefreshLayout pic 6)

    setProgressBackgroundColorSchemeResource(@ColorRes int colorRes)
    setProgressBackgroundColorSchemeColor(@ColorInt int color)
    setColorSchemeResources(@ColorRes int... colorResIds)

####Footer
BallPulseView(pic 2)

    setNormalColor(@ColorInt int color)
    setAnimatingColor(@ColorInt int color)

LoadingView(pic 3)

更多动效可以参考AVLoadingIndicatorView库。
3.实现个性化的Header和Footer

相关接口分别为IHeaderView和IBottomView,代码如下:

public interface IHeaderView {
    View getView();

    void onPullingDown(float fraction,float maxHeadHeight,float headHeight);

    void onPullReleasing(float fraction,float maxHeadHeight,float headHeight);

    void startAnim(float maxHeadHeight,float headHeight);

    void reset();
}

其中getView()方法用于在TwinklingRefreshLayout中获取到实际的Header,因此不能返回null。

实现像新浪微博那样的刷新效果(有部分修改,具体请看源码),实现代码如下:

1.首先定义SinaRefreshHeader继承自FrameLayout并实现IHeaderView方法

2.getView()方法中返回this

3.在onAttachedToWindow()或者构造函数方法中获取一下需要用到的布局

    在onFinish()方法中调用listener.onAnimEnd()。此方法的目的是为了在finish之前可以执行一段动画。

private void init() {
        View rootView = View.inflate(getContext(), R.layout.view_sinaheader, null);
        refreshArrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
        refreshTextView = (TextView) rootView.findViewById(R.id.tv);
        loadingView = (ImageView) rootView.findViewById(R.id.iv_loading);
        addView(rootView);
    }

4.实现其它方法

@Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1f) refreshTextView.setText(pullDownStr);
        if (fraction > 1f) refreshTextView.setText(releaseRefreshStr);
        refreshArrow.setRotation(fraction * headHeight / maxHeadHeight * 180);


    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1f) {
            refreshTextView.setText(pullDownStr);
            refreshArrow.setRotation(fraction * headHeight / maxHeadHeight * 180);
            if (refreshArrow.getVisibility() == GONE) {
                refreshArrow.setVisibility(VISIBLE);
                loadingView.setVisibility(GONE);
            }
        }
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        refreshTextView.setText(refreshingStr);
        refreshArrow.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
    }

    @Override
        public void onFinish(OnAnimEndListener listener) {
            listener.onAnimEnd();
        }

5.布局文件

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center">
    <ImageView
        android:id="@+id/iv_arrow"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_arrow"/>

    <ImageView
        android:id="@+id/iv_loading"
        android:visibility="gone"
        android:layout_width="34dp"
        android:layout_height="34dp"
        android:src="@drawable/anim_loading_view"/>

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="16dp"
        android:textSize="16sp"
        android:text="下拉刷新"/>
</LinearLayout>

注意fraction的使用,比如上面的代码refreshArrow.setRotation(fraction * headHeight / maxHeadHeight * 180)，fraction * headHeight表示当前头部滑动的距离，然后算出它和最大高度的比例，然后乘以180，可以使得在滑动到最大距离时Arrow恰好能旋转180度。

onPullingDown/onPullingUp表示正在下拉/正在上拉的过程。 onPullReleasing表示向上拉/下拉释放时回调的状态。 startAnim则是在onRefresh/onLoadMore之后才会回调的过程（此处是显示了加载中的小菊花）

如上所示，轻而易举就可以实现一个个性化的Header或者Footer。（更简单的实现请参考Demo中的 TextHeaderView(图四)）。
NestedScroll
TwinklingRefreshLayout嵌套CoordinatorLayout

---layout

<?xml version="1.0" encoding="utf-8"?>
<com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/refresh"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.CoordinatorLayout
        android:id="@+id/coord_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:addStatesFromChildren="true"
        android:fitsSystemWindows="true">

        <android.support.design.widget.AppBarLayout
            android:id="@+id/appbar_layout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:clipChildren="false">

            <!--...-->

        </android.support.design.widget.AppBarLayout>

        <android.support.v7.widget.RecyclerView
            android:id="@+id/recyclerview"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior" />

    </android.support.design.widget.CoordinatorLayout>
</com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout>

--- 代码1

refreshLayout.setTargetView(rv);

让refreshLayout能够找到RecyclerView/ListView

--- 代码2

AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset >= 0) {
            refreshLayout.setEnableRefresh(true);
            refreshLayout.setEnableOverScroll(false);
        } else {
            refreshLayout.setEnableRefresh(false);
            refreshLayout.setEnableOverScroll(false);
        }
    }
});

设置AppBarLayout的移动监听器，需要下拉显示AppBarLayout时需设置setEnableRefresh(false),setEnableOverScroll(false)；AppBarLayout隐藏后还原为原来设置的值即可。

####CoordinatorLayout嵌套TwinklingRefreshLayout --- layout

<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/coord_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:addStatesFromChildren="true"
    android:fitsSystemWindows="true">

    <com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
        android:id="@+id/refresh"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <android.support.v7.widget.RecyclerView
            android:id="@+id/recyclerview"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout>

</android.support.design.widget.CoordinatorLayout>

注意给TwinklingRefreshLayout设置一个layout_behavior="@string/appbar_scrolling_view_behavior"