package dangusgame.model;

import java.util.ArrayList;
import java.util.List;

public class Item {
	
	public enum Tipos {mao,duasMaos,acessorio,armadura};
	
	private String nome;
	private Tipos tipo;
	private Stats stats;
	
	public Item(String nm, Tipos tp, Stats st)
	{
		tipo=tp;
		nome=nm;
		stats=st;
	
	}
	
	public Item()
	{
		tipo=Tipos.valueOf("mao");
		nome="";
		stats=new Stats();
	
	}
	
	public Item(List<String> str)
	{
		tipo=Tipos.valueOf(str.get(0));
		nome=str.get(1);
		stats.load((ArrayList<String>) str.subList(2, 12));
	
	}
	
	public String toString()
	{
		return nome;
	}
	
	public String getTipo()
	{
		return tipo.toString();
	}

}
