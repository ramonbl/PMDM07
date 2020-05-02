package com.ramonbl.practica3d00;

import com.badlogic.gdx.Game;

public class PracticasNovaApi3D extends Game {
  private EX_2_CargaModelosInicial pantallaCargaModelosInicial;
	private EX_3_MoverModelos pantallaMoverModelos;
	private PMDM07 pantallaPMDM07;

	@Override
	public void create() {
		// TODO Auto-generated method stub

		pantallaCargaModelosInicial = new EX_2_CargaModelosInicial();
		pantallaMoverModelos = new EX_3_MoverModelos();
		pantallaPMDM07 = new PMDM07();

//		setScreen(pantallaCargaModelosInicial);
//		setScreen(pantallaMoverModelos);
		setScreen(pantallaPMDM07);
	}

	@Override
	public void dispose(){
		super.dispose();
		pantallaCargaModelosInicial.dispose();
		pantallaMoverModelos.dispose();
		pantallaPMDM07.dispose();
	}
}
