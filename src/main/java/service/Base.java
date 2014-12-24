package service;

import java.util.ArrayList;

/**
 * Created by Дмитрий on 31.07.14.
 */
public class Base {

   private ArrayList<Element> baseList = new ArrayList(); // Список элементов

    // Получить базу
    public ArrayList<Element> getBaseList() {
        return baseList;
    }
    // Загрузить базу
    public void setBaseList(ArrayList<Element> baseList) {
        this.baseList = baseList;
    }
    // Добавить элемент в базу
    public void addElemInBase(Element element) {
        baseList.add(element);
    }
    // Взять элемент из базы
    public Element getElemFromBase(int numberOfElem) {
        return baseList.get(numberOfElem);
    }
    // Удалить элемент из базы
    public void removeElemFromBase(int numberOfElem) {
        baseList.remove(numberOfElem);
    }

}
