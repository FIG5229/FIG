package com.embracesource.traffic.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 上午 09:02
 * @description：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    private List<Role> roles;

    private String url;
}
