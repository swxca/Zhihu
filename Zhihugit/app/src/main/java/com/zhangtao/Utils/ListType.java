package com.zhangtao.Utils;

import android.text.TextUtils;

/**
 * Created by zhangtao on 15/11/29.
 * 一个类是作者按照最最主要的思想,经过许多设计模式,还有数据间的调用方法,
 * 作者个人的表达习惯等等组成的.我们看一个库要做的就是拿到这个思想.拿到
 * 一个库虽然代码可能会比较多,但是大多数代码只是为了实现这个思想而编写的,
 * 根本没必要在意这些.
 * 学习一个库也不要觉得看过了就完事,而是敲出来.
 * 所以:看一个库(类)
 * 1.找到里面你不会的思想,理解它.
 * 2.就和吃饭一样捡一捡里面还有哪些你爱吃的,比如设计模式,代码规范
 * 3.关上别人的库,自己实现一遍.用自己的思想+别人的思想.
 */
public class ListType {
    //观察我们的drawer有四种类型,header,有icon的item,无icon得item,一条线
    private static final int NO_ICON = 0;
    private static final int NO_HEADER = 0;

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ICON = 1;
    public static final int TYPE_NO_ICON = 2;
    public static final int TYPE_SEPARATOR = 3;
    public  int type;
    public String name;
    public int icon;
    public String header;


    public ListType(String header, String name, int icon) {
        this.name = name;
        this.icon = icon;

        if (TextUtils.isEmpty(header) && TextUtils.isEmpty(name) && icon == NO_ICON) {
            this.type = TYPE_SEPARATOR;
        } else if (icon == NO_ICON && TextUtils.isEmpty(header)) {
            this.type = TYPE_NO_ICON;
        } else if (TextUtils.isEmpty(header)) {
            this.type = TYPE_ICON;
        } else {
            this.type = TYPE_HEADER;
            this.header=header;
        }

        if (type != TYPE_SEPARATOR && TextUtils.isEmpty(name)) {
            throw new IllegalArgumentException("you need set a name or icon or header for a non-SEPARATOR item");
        }
    }

    public ListType(String name, int icon) {
        this(null, name, icon);
    }

    public ListType(String name) {
        this(null, name, NO_ICON);
    }

    public ListType() {
        this(null);
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }
    public String getHeader(){
        return header;
    }

}
