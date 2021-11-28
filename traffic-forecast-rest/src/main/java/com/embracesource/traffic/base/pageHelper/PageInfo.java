package com.embracesource.traffic.base.pageHelper;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/21 11:02
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageSerializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;
import java.util.List;

@ApiModel(value = "分页数据")
public class PageInfo<T> extends PageSerializable<T> {

    @ApiModelProperty(value = "页数")
    private int pageNum;
    @ApiModelProperty(value = "分页大小")
    private int pageSize;
    @ApiModelProperty(value = "页数")
    private int size;
    @ApiModelProperty(value = "总页数")
    private int pages;




    public PageInfo(List<T> list) {
        this(list, 8);
    }

    public PageInfo(List<T> list, int navigatePages) {
        super(list);

        if (list instanceof Page) {
            Page page = (Page)list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
            this.size = page.size();

        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();

            this.size = list.size();

        }


    }

    public PageInfo() {

    }

    public static <T> com.github.pagehelper.PageInfo<T> of(List<T> list) {
        return new com.github.pagehelper.PageInfo(list);
    }

    public static <T> com.github.pagehelper.PageInfo<T> of(List<T> list, int navigatePages) {
        return new com.github.pagehelper.PageInfo(list, navigatePages);
    }





    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PageInfo{");
        sb.append("pageNum=").append(this.pageNum);
        sb.append(", pageSize=").append(this.pageSize);
        sb.append(", size=").append(this.size);
        sb.append(", total=").append(this.total);
        sb.append(", list=").append(this.list);
        sb.append(", navigatepageNums=");
        sb.append('}');
        return sb.toString();
    }
}
