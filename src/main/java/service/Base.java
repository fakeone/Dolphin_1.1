package service;

import java.util.ArrayList;

/**
 * Created by ������� on 31.07.14.
 */
public class Base {

   private ArrayList<Element> baseList = new ArrayList(); // ������ ���������

    // �������� ����
    public ArrayList<Element> getBaseList() {
        return baseList;
    }
    // ��������� ����
    public void setBaseList(ArrayList<Element> baseList) {
        this.baseList = baseList;
    }
    // �������� ������� � ����
    public void addElemInBase(Element element) {
        baseList.add(element);
    }
    // ����� ������� �� ����
    public Element getElemFromBase(int numberOfElem) {
        return baseList.get(numberOfElem);
    }
    // ������� ������� �� ����
    public void removeElemFromBase(int numberOfElem) {
        baseList.remove(numberOfElem);
    }

}
