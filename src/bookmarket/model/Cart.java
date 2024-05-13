package bookmarket.model;

public class Cart {

	CartItem[] itemList = new CartItem[64];
	int numItem = 0;
	
	public boolean isEmpty() {
		return numItem == 0;
	}

	public int getNumItem() {
		return numItem;
	}

	public void setNumItem(int numItem) {
		this.numItem = numItem;
	}
	
	public CartItem[] getItemList() {
		return itemList;
	}

	public void setItemList(CartItem[] itemList) {
		this.itemList = itemList;
	}

	public String getItemInfo(int index) {
		return itemList[index].toString();
	}

	public void resetCart() {
		
		numItem = 0;
		
		this.itemList = new CartItem[64];
	}

	public void addItem(Book book) {

		CartItem item = getCartItem(book);
		if (item == null) {
			itemList[numItem++] = new CartItem(book);
		} else {
			item.addQuantity(1);
		}
		
		
	}

	private CartItem getCartItem(Book book) {
		
		for(int i = 0; i < numItem; i++) {
			if (itemList[i].getBook() == book)
				return itemList[i];
		}
		
		return null;
	}

	
}
