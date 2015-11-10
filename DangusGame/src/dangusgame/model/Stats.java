package dangusgame.model;


import java.util.List;

public class Stats {
	private int fisico,agilidade,inteligencia,resistencia,movimento,distancia;
	private float carisma;
	private float XPFisico,XPAgilidade,XPInteligencia,XPResistencia;
	
	//construtor padrao para a construção de um dango
	public Stats(int fs, int agi,int intl, int res, int mov, int dis, float cr, float xf, float xa, float xi, float xr)
	{
		fisico=fs;
		setAgilidade(agi);
		setInteligencia(intl);
		setResistencia(res);
		setMovimento(mov);
		setDistancia(dis);
		setCarisma(cr);
		setXPFisico(xf);
		setXPAgilidade(xa);
		setXPInteligencia(xi);
		setXPResistencia(xr);
		
		
	}
	
	public Stats()
	{
		fisico=0;
		setAgilidade(0);
		setInteligencia(0);
		setResistencia(0);
		setMovimento(0);
		setDistancia(0);
		setCarisma(0);
		setXPFisico(0);
		setXPAgilidade(0);
		setXPInteligencia(0);
		setXPResistencia(0);
		
	}
	
	//construtor padrao para itens
	public Stats(int fs, int agi,int intl, int res, int mov, int dis, float cr )
	{
		fisico=fs;
		setAgilidade(agi);
		setInteligencia(intl);
		setResistencia(res);
		setMovimento(mov);
		setDistancia(dis);
		setCarisma(cr);
		setXPFisico(0);
		setXPAgilidade(0);
		setXPInteligencia(0);
		setXPResistencia(0);
		
	}
	
	//construtor padrao para itens simples
	public Stats(int fs, int agi,int intl, int res)
	{
		fisico=fs;
		setAgilidade(agi);
		setInteligencia(intl);
		setResistencia(res);
		setMovimento(0);
		setDistancia(0);
		setCarisma(0);
		setXPFisico(0);
		setXPAgilidade(0);
		setXPInteligencia(0);
		setXPResistencia(0);
			
	}
	
	public void soma (Stats st)
	{
		fisico+=st.getFisico();
		agilidade+=st.getAgilidade();
		
		inteligencia+=st.getInteligencia();
		resistencia+=st.getResistencia();
		movimento+=st.getMovimento();
		distancia+=st.getDistancia();
		carisma+=st.getCarisma();
		XPFisico+=st.getXPFisico();
		XPAgilidade+=st.getXPAgilidade();
		XPInteligencia+=st.getXPInteligencia();
		XPResistencia+=st.getXPResistencia();
		
	}

	public String SaveString()
	{
		return fisico+";"+agilidade+";"+inteligencia+";"+resistencia+";"+movimento+";"+distancia+";"+carisma+";"+XPFisico+";"+XPAgilidade+";"+XPInteligencia+";"+XPResistencia+";";
	}
	
	public void load(List<String> list)
	{
		for(String str: list)
			System.out.println(str);
		
		fisico=Integer.parseInt(list.get(0));		
		setAgilidade(Integer.parseInt(list.get(1)));
		setInteligencia(Integer.parseInt(list.get(2)));
		setResistencia(Integer.parseInt(list.get(3)));
		setMovimento(Integer.parseInt(list.get(4)));
		setDistancia(Integer.parseInt(list.get(5)));
		setCarisma(Float.parseFloat(list.get(6)));
		setXPFisico(Float.parseFloat(list.get(7)));
		setXPAgilidade(Float.parseFloat(list.get(8)));
		setXPInteligencia(Float.parseFloat(list.get(9)));
		setXPResistencia(Float.parseFloat(list.get(10)));
	}
	
	public int getFisico() {
		return fisico;
	}

	public void setFisico(int fisico) {
		this.fisico = fisico;
	}
	
	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getMovimento() {
		return movimento;
	}

	public void setMovimento(int movimento) {
		this.movimento = movimento;
	}

	public int getResistencia() {
		return resistencia;
	}

	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}

	public int getAgilidade() {
		return agilidade;
	}

	public void setAgilidade(int agilidade) {
		this.agilidade = agilidade;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public float getCarisma() {
		return carisma;
	}

	public void setCarisma(float carisma) {
		this.carisma = carisma;
	}

	public float getXPResistencia() {
		return XPResistencia;
	}

	public void setXPResistencia(float xPResistencia) {
		XPResistencia = xPResistencia;
	}

	public float getXPFisico() {
		return XPFisico;
	}

	public void setXPFisico(float xPFisico) {
		XPFisico = xPFisico;
	}

	public float getXPAgilidade() {
		return XPAgilidade;
	}

	public void setXPAgilidade(float xPAgilidade) {
		XPAgilidade = xPAgilidade;
	}

	public float getXPInteligencia() {
		return XPInteligencia;
	}

	public void setXPInteligencia(float xPInteligencia) {
		XPInteligencia = xPInteligencia;
	}
	
	
}
