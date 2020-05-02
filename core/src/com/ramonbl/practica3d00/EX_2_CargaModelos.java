package com.ramonbl.practica3d00;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class EX_2_CargaModelos implements Screen {
	private PerspectiveCamera camara3d;

	public EX_2_CargaModelos(){
		camara3d = new PerspectiveCamera();

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

		camara3d.fieldOfView=67;
		camara3d.viewportWidth=width;
		camara3d.viewportHeight=height;

		camara3d.position.set(0f,0f,15f);
		camara3d.lookAt(0,0,0);
		camara3d.near=1;
		camara3d.far=300f;
		camara3d.update();



	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
