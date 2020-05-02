package com.ramonbl.practica3d00;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.Array;



public class EX_2_CargaModelosInicial implements Screen {

	private PerspectiveCamera camara3d;
	private  ModelBatch modelBatch;

	private Array<ModelInstance> instances;


	private Environment environment;
	private CameraInputController camController;

	public EX_2_CargaModelosInicial(){
		camara3d = new PerspectiveCamera();
		camController = new CameraInputController(camara3d);
		Gdx.input.setInputProcessor(camController);

		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(1f, 1f, 1f, 1f, 0f, 0f));

		instances = new Array<ModelInstance>();

		AssetManager assets = new AssetManager();
		assets.load("ship.obj", Model.class);
		assets.load("cube.g3db", Model.class);
		assets.finishLoading();

		Model modelNave = assets.get("ship.obj", Model.class);
		Model modelCubo = assets.get("cube.g3db", Model.class);

		instances.add(new ModelInstance(modelNave));
		instances.add(new ModelInstance(modelCubo));

		instances.get(0).transform.setToTranslation(2, 2, 2);



	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camController.update();

		modelBatch.begin(camara3d);

		modelBatch.render(instances,environment);

		modelBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

		camara3d.fieldOfView=67;
		camara3d.viewportWidth=width;
		camara3d.viewportHeight=height;

		Gdx.input.setInputProcessor(camController);

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

		modelBatch.dispose();
		instances.clear();


	}

}