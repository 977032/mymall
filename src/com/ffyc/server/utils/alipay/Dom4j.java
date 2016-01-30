package com.ffyc.server.utils.alipay;

import java.net.URL;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Dom4j
{
  String filepath = "";
  
  public String DomXml(String filepath)
    throws Exception
  {
    SAXReader reader = new SAXReader();
    Document doc = reader.read(new URL(filepath).openStream());
    

    List<Node> nodeList = doc.selectNodes("//request/*");
    StringBuffer buf = new StringBuffer();
    for (Node node : nodeList)
    {
      buf.append("<html><p>");
      buf.append(node.getName()).append("=").append(node.getText())
        .append("<html><p>");
    }
    List<Node> nodeList1 = doc.selectNodes("//response/tradeBase/*");
    StringBuffer buf1 = new StringBuffer();
    for (Node node : nodeList1)
    {
      buf1.append("<html><p>");
      


      buf1.append(node.getName()).append("=").append(node.getText())
        .append("<html><p>");
    }
    return 
      "request信息：" + buf.toString() + "response信息：" + buf1.toString();
  }
  
  public static void main(String[] args)
    throws Exception
  {}
}
