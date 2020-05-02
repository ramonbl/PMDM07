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

public class PMDM07 implements Screen{
	private PerspectiveCamera camara3d; //cámara para ver escena 3d desde cierta perspectiva
	private  ModelBatch modelBatch;

	private Array<ModelInstance> instancesPlanet;
	private ModelInstance instanceSol;
	private ModelInstance instanceMercurio;
	private ModelInstance instanceVenus;
	private ModelInstance instanceTierra;

	private Environment environment;
	private CameraInputController camController;

	private float pos;
	private float posSolX=0;
	private float posMercurio=0;
	private float posVenus=0;
	private float posTierra=0;

	public PMDM07(){
		camara3d = new PerspectiveCamera();
		camController = new CameraInputController(camara3d);
		Gdx.input.setInputProcessor(camController);

		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(1f, 1f, 1f, 1f, 0f, 0f));

		//Array para todas los planetas (que tiene todos el mismo asset)
		//instances = new Array<ModelInstance>();
		//instancesPlanet = new Array<ModelInstance>();

		//Carga de modelos mediante AssetManager
		AssetManager assets = new AssetManager();
		assets.load("sun.obj", Model.class);
		assets.load("mercurio.obj", Model.class);
		assets.load("venus.obj", Model.class);
		assets.load("cube.g3db", Model.class);
		assets.load("earth.obj", Model.class);
		assets.finishLoading();

		//Obtención del modelo a partir del modelo cargado
		Model modelSol = assets.get("sun.obj", Model.class);

		Model modelMercurio = assets.get("mercurio.obj", Model.class);

		Model modelVenus = assets.get("venus.obj", Model.class);

		Model modelTierra = assets.get("earth.obj", Model.class);


		//Creación del ModelInstance a partir del modelo de la nave y hacemos rotación en Y de 90 grados para ponerla hacia los cubos
		//instanceCubo = new ModelInstance(modelCubo);
		instanceSol = new ModelInstance(modelSol);
		instanceSol.transform.setToScaling(3f,3f,3f); //sol más grande
		instanceSol.transform.setToRotation(0, 1, 0, 0); //Sol sin rotación

		instanceMercurio = new ModelInstance(modelMercurio);
//		instanceMercurio.transform.setTranslation(5,0,0);
		instanceMercurio.transform.setToRotation(1,1,1,45);
		instanceMercurio.transform.setToScaling(0.5f, 0.5f,0.5f); //la mitad de tamaño original

		instanceVenus = new ModelInstance(modelVenus);
		//instanceVenus.transform.setTranslation(5,0,0);
		instanceVenus.transform.setToRotation(1, 1, 1, 30);
		instanceVenus.transform.setToScaling(0.3f, 0.3f, 0.3f); //la mitad de tamaño original

		instanceTierra = new ModelInstance(modelTierra);
		//instanceTierra.transform.setTranslation(5,0,0);
		instanceTierra.transform.setToRotation(1, 1, 1, 30);
		instanceTierra.transform.setToScaling(0.6f, 0.6f, 0.6f); //la mitad de tamaño original
}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camController.update();

		pos+=delta * 1f; //posición de la nave (para mov traslación)
		posSolX =0;

		float mercurioDistancia = 3;
		posMercurio +=delta *0.5f;

		float venusDistancia = 5;
		posVenus += delta * 0.7f;

		float tierraDistancia = 8;
		posTierra += delta * 0.3f;

		instanceSol.transform.setTranslation(posSolX,0,0);

		instanceMercurio.transform.rotate(0,1,0,30);
		instanceMercurio.transform.setTranslation(
				(float)(mercurioDistancia*Math.sin(posMercurio)),
				0,
				(float)(mercurioDistancia*Math.cos(posMercurio)));


		instanceVenus.transform.rotate(0,1,0,30);
		instanceVenus.transform.setTranslation(
				(float)(venusDistancia*Math.sin(posVenus)),
				0,
				(float)(venusDistancia*Math.cos(posVenus)));

		instanceTierra.transform.rotate(0,1,0,30);
		instanceTierra.transform.setTranslation(
				(float)(tierraDistancia*Math.sin(posTierra)),
				0,
				(float)(tierraDistancia*Math.cos(posTierra)));

		//Hacemos que cámara siga a la nave
		//camara3d.lookAt(pos,0,0);
		camara3d.lookAt(posSolX,0,0);
		camara3d.update();

		modelBatch.begin(camara3d);

		//Renderizar el modelInstance
		//modelBatch.render(instancesPlanet,environment);
		modelBatch.render(instanceSol,environment);
		modelBatch.render(instanceMercurio);
		modelBatch.render(instanceVenus);
		modelBatch.render(instanceTierra);

		modelBatch.end();
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

		camara3d.fieldOfView=67;
		camara3d.viewportHeight=height;
		camara3d.viewportWidth=width;

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
		instancesPlanet.clear();


	}

}
