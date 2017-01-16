package superclasses;

public abstract class EntityItem extends Entity {
	private float summa;
	
	public EntityItem(int id, float summa) {
		super(id);
		
		this.summa = summa;
	}
	
	public float getSumma() {
		return summa;
	}

	public void setSumma(float summa) {
		this.summa = summa;
	}

}