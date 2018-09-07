package com.odbpo.fenggou.swiperecyclerviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.odbpo.fenggou.swiperecyclerviewdemo.adapter.MyAdapter;
import com.odbpo.fenggou.swiperecyclerviewdemo.util.InitDataUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rv)
    SwipeMenuRecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRV();
    }

    public void initRV() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        setSwipeMenu();
        rv.setAdapter(new MyAdapter(InitDataUtil.getFData()));
    }

    public void setSwipeMenu() {
        // 设置监听器。
        rv.setSwipeMenuCreator(mSwipeMenuCreator);
        // 菜单点击监听。
        rv.setSwipeMenuItemClickListener(mMenuItemClickListener);
        //真实网络请求数据返回写法
//        rv.setSwipeMenuItemClickListener(new MySwipeMenuItemClickListener(new ArrayList<Integer>()));
    }

    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.swipe_menu_width);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(MainActivity.this)
                        .setBackground(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。

                SwipeMenuItem addItem = new SwipeMenuItem(MainActivity.this)
                        .setBackground(R.drawable.selector_green)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                //swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(MainActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(MainActivity.this, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };

//    /**
//     * 真实网络请求数据返回写法
//     */
//    class MySwipeMenuItemClickListener implements SwipeMenuItemClickListener{
//        private List<Integer> mData;
//
//        public MySwipeMenuItemClickListener(List<Integer> mData) {
//            this.mData = mData;
//        }
//
//        @Override
//        public void onItemClick(SwipeMenuBridge menuBridge) {
//            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
//            menuBridge.closeMenu();
//
//            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
//            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
//            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
//
//            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
////                Toast.makeText(MainActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "list第" + mData.get(adapterPosition) + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
////                Toast.makeText(MainActivity.this, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "list第" + mData.get(adapterPosition) + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

}
