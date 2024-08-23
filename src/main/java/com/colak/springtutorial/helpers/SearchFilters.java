package com.colak.springtutorial.helpers;


import java.util.List;

/**
 * Description: Use to build FilterCondition list-based controller params
 */
public record SearchFilters(List<FilterCondition> filterAndConditions, List<FilterCondition> filterOrConditions) {
}
