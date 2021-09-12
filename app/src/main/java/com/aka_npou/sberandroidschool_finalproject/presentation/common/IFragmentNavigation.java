package com.aka_npou.sberandroidschool_finalproject.presentation.common;

/**
 * Интерфейс для обработки перехода между фрагментами
 * @author Мулярчук Александр
 */
public interface IFragmentNavigation {
    /**
     * Заменяет один текущий фрагмент на другой
     * @param tagFragment tag фрагмента, которым надо заменить текущий фрагмент
     * @param addToBackStack признак добавления в BackStack
     */
    void replace(String tagFragment, boolean addToBackStack);
}
