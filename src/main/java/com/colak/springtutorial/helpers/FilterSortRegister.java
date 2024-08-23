package com.colak.springtutorial.helpers;


/**
 * @param page      initial page
 * @param size      paging item size
 * @param filterOr  String filter or conditions.
 * @param filterAnd String filter and conditions.
 * @param orders    sorting orders
 */
public record FilterSortRegister(int page, int size, String filterOr, String filterAnd, String orders) {
}
