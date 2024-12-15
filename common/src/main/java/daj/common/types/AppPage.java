package daj.common.types;

import java.util.List;

import lombok.Getter;

@Getter
public class AppPage<E> {
  private List<E> content;
  private int totalElements;
  private int number;

  public AppPage(List<E> content, int totalElements, int number) {
    this.content = content;
    this.totalElements = totalElements;
    this.number = number;
  }

  public int getTotalPages() {
    return totalElements / this.getSize();
  }

  public int getSize() {
    return content.size();
  }


}
