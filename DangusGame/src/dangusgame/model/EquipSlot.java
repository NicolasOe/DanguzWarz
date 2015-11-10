package dangusgame.model;

public class EquipSlot {

	Boolean enabled;
	Item item;
	
	public EquipSlot(){
		enabled = true;
		item = null;
	}
	
	public EquipSlot(Item item){
		enabled = false;
		this.item = item;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
