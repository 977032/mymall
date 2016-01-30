package com.ffyc.server.utils;

import java.util.List;

public class PaginationSupport
{
  public static final int PAGESIZE = 30;
  private int pageSize = 30;
  private List items;
  private int totalCount;
  private int[] indexes = new int[0];
  private int startIndex = 0;
  private int pageCount;
  private int currentPage = 0;
  private int pageNumber;
  private int[] pages = new int[0];
  
  public PaginationSupport(List items, int totalCount)
  {
    setPageSize(30);
    setTotalCount(totalCount);
    setItems(items);
    setStartIndex(0);
  }
  
  public PaginationSupport(List items, int totalCount, int startIndex)
  {
    setPageSize(30);
    setTotalCount(totalCount);
    setItems(items);
    setStartIndex(startIndex);
  }
  
  public PaginationSupport(List items, int totalCount, int startIndex,int pageSize)
  {
    setPageSize(pageSize);
    setTotalCount(totalCount);
    setItems(items);
    setStartIndex(startIndex);
  }
  
  public List getItems()
  {
    return this.items;
  }
  
  public void setItems(List items)
  {
    this.items = items;
  }
  
  public int getPageSize()
  {
    return this.pageSize;
  }
  
  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }
  
  public int getTotalCount()
  {
    return this.totalCount;
  }
  
  public void setTotalCount(int totalCount)
  {
    if (totalCount > 0)
    {
      this.totalCount = totalCount;
      int count = totalCount / this.pageSize;
      if (totalCount % this.pageSize > 0) {
        count++;
      }
      this.indexes = new int[count];
      for (int i = 0; i < count; i++) {
        this.indexes[i] = (this.pageSize * i);
      }
    }
    else
    {
      this.totalCount = 0;
    }
  }
  
  public int[] getIndexes()
  {
    return this.indexes;
  }
  
  public void setIndexes(int[] indexes)
  {
    this.indexes = indexes;
  }
  
  public int getStartIndex()
  {
    return this.startIndex;
  }
  
  public void setStartIndex(int startIndex)
  {
    this.pageNumber = (startIndex / this.pageSize + 1);
    if (this.totalCount <= 0)
    {
      this.startIndex = 0;
    }
    else if (startIndex >= this.totalCount)
    {
      this.startIndex = this.indexes[(this.indexes.length - 1)];
    }
    else if (startIndex < 0)
    {
      this.startIndex = 0;
    }
    else
    {
      this.startIndex = this.indexes[(startIndex / this.pageSize)];
      this.currentPage = (startIndex / this.pageSize + 1);
    }
  }
  
  public int getNextIndex()
  {
    int nextIndex = getStartIndex() + this.pageSize;
    if (nextIndex >= this.totalCount) {
      return getStartIndex();
    }
    return nextIndex;
  }
  
  public int getPreviousIndex()
  {
    int previousIndex = getStartIndex() - this.pageSize;
    if (previousIndex < 0) {
      return 0;
    }
    return previousIndex;
  }
  
  public int getPageCount()
  {
    return this.indexes.length;
  }
  
  public int getCurrentPage()
  {
    return this.currentPage;
  }
  
  public int getPageNumber()
  {
    return this.pageNumber;
  }
  
  public void setPageNumber(int pageNumber)
  {
    this.pageNumber = pageNumber;
  }
  
  public int getNextPage()
  {
    int nextPage = this.pageNumber + 1;
    if (nextPage > getPageCount()) {
      nextPage = getPageCount();
    }
    return nextPage;
  }
  
  public int getPreviousPage()
  {
    int prePage = this.pageNumber - 1;
    if (prePage < 1) {
      prePage = 1;
    }
    return prePage;
  }
  
  public int getNextTenPage()
  {
    int nextPage = this.pageNumber + 10;
    if (nextPage > getPageCount()) {
      nextPage = getPageCount();
    }
    return nextPage;
  }
  
  public int getPreviousTenPage()
  {
    int prePage = this.pageNumber - 10;
    if (prePage < 1) {
      prePage = 1;
    }
    return prePage;
  }
  
  public int[] getPages()
  {
    int first = 1;
    int end = this.pageNumber + 2;
    if (this.pageNumber - 2 > 0) {
      first = this.pageNumber - 2;
    }
    if (end > getPageCount()) {
      end = getPageCount();
    }
    if ((end - first < 4) && (first + 4 <= getPageCount())) {
      end = first + 4;
    }
    if ((end - first < 4) && (end - 4 >= 1)) {
      first = end - 4;
    }
    int fornum = end - first + 1;
    this.pages = new int[fornum];
    for (int i = 0; i < fornum; i++)
    {
      this.pages[i] = first;
      first++;
    }
    return this.pages;
  }
}
