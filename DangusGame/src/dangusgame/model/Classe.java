package dangusgame.model;

public enum Classe {
	ARQUEIRO("Arqueiro", new Stats(2,5,4,1,1,1,0)),
	GUERREIRO("Guerreiro", new Stats(5,2,1,4,1,1,0)),
	MAGO("Mago", new Stats(1,2,6,2,2,1,0)),
	FABULOSO("Fabuloso", new Stats(20,20,20,20,3,0,2000));
	
	final private String name;
	final private Stats stats;
	
	
	Classe(String s,Stats st)
	{
		name=s;
		stats=st;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	public Stats getStats()
	{
		return stats;
	}
};