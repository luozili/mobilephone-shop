/**
  * Copyright 2019 bejson.com 
  */
package net.hycollege.message.bean.ali;
import java.util.Date;

/**
 * Auto-generated: 2019-11-24 15:33:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class AliGoods {

    private Date goods_id;
    private String goods_name;
    private String goods_category;
    private String price;
    private String quantity;
    public void setGoods_id(Date goods_id) {
         this.goods_id = goods_id;
     }
     public Date getGoods_id() {
         return goods_id;
     }

    public void setGoods_name(String goods_name) {
         this.goods_name = goods_name;
     }
     public String getGoods_name() {
         return goods_name;
     }

    public void setGoods_category(String goods_category) {
         this.goods_category = goods_category;
     }
     public String getGoods_category() {
         return goods_category;
     }

    public void setPrice(String price) {
         this.price = price;
     }
     public String getPrice() {
         return price;
     }

    public void setQuantity(String quantity) {
         this.quantity = quantity;
     }
     public String getQuantity() {
         return quantity;
     }

}