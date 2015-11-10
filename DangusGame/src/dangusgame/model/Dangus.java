package dangusgame.model;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dangusgame.core.MyActor;

public class Dangus
{
	
	private final int SLOTS_QTT = 4;
	private String nome;
	private EquipSlot itens[];
	private Stats stats;
	private Color color;
	private Classe classe;
	transient MyActor actor;
	
	public Dangus(String nm, Stats st)
	{	
		classe = Classe.FABULOSO;
		nome=nm;
		color=Color.WHITE;
		stats=st;
		actor = new MyActor(new TextureRegion(new Texture(Gdx.files.internal("dangoPadrao_fit.png"))));
		actor.setColor(color);
		itens = new EquipSlot[SLOTS_QTT];
	}
	
	public Dangus(Dangus d)
	{	
		classe = d.getClasse();
		nome=d.getNome();
		color=d.getCor();
		stats=d.getStats();
		actor = new MyActor(new TextureRegion(new Texture(Gdx.files.internal("dangoPadrao_fit.png"))));
		actor.setColor(color);
		itens = d.getEquips();
	}
	
	public Dangus()
	{	
		classe = Classe.FABULOSO;
		nome="";
		color=Color.WHITE;
		stats=new Stats();
		actor = new MyActor(new TextureRegion(new Texture(Gdx.files.internal("dangoPadrao_fit.png"))));
		actor.setColor(color);
		itens = new EquipSlot[SLOTS_QTT];
	}
	
	public Dangus(String nm)
	{	
		classe = Classe.FABULOSO;
		
		actor = new MyActor(new TextureRegion(new Texture(Gdx.files.internal("dangoPadrao_fit.png"))));
		nome=nm;
		color=Color.WHITE;
		System.out.println(nome+" foi criado!");
		stats=new Stats();
		actor.setColor(color);
		itens = new EquipSlot[SLOTS_QTT];
	}
	
	public String toString()
	{
		return nome;
	}
	
	public Item getItem(int i)
	{
		if (i<SLOTS_QTT)
			return itens[i].getItem();
		else
			return null;
	
	}
	
	public Stats getStats()
	{
		return stats;
	}
	
	public void setCor(Color c)
	{
		color=c;
		actor.setColor(c);
	}
	
	public Color getCor()
	{
		return color;
	}
	
	public MyActor getActor()
	{
		return actor;
	}
	
	public void setNome(String n)
	{
		nome=n;	
	}
	
	public Classe getClasse()
	{
		return classe;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public EquipSlot[] getEquips()
	{
		return itens;
	}
	
	public void setClasse(Classe c)
	{
		classe=c;
		
	}
	
	
	
}
