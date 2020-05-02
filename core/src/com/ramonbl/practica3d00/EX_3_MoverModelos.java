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

public class EX_3_MoverModelos implements Screen {
	private PerspectiveCamera camara3d;
	private  ModelBatch modelBatch;

	private Array<ModelInstance> instances;
	private ModelInstance instanceNave;


	private Environment environment;
	private CameraInputController camController;

	private float pos;

	public EX_3_MoverModelos(){
		camara3d = new PerspectiveCamera();
		camController = new CameraInputController(camara3d);
		Gdx.input.setInputProcessor(camController);

		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(1f, 1f, 1f, 1f, 0f, 0f));

		instances = new Array<ModelInstance>();

		//Carga de modelos mediante AssetManager
		AssetManager assets = new AssetManager();
		assets.load("ship.obj", Model.class);
		assets.load("cube.g3db", Model.class);
		assets.finishLoading();

		//Obtención del modelo a partir del modelo cargado
		Model modelNave = assets.get("ship.obj", Model.class);
		Model modelCubo = assets.get("cube.g3db", Model.class);

		//Creación del ModelInstance a partir del modelo de la nave y hacemos rotación en Y de 90 grados para ponerla hacia los cubos
		instanceNave = new ModelInstance(modelNave);
		instanceNave.transform.setToRotation(0, 1, 0, 90);

		//Creación del Array de ModelInstance. Los trasladamos y al 4 y 5 los escalamos a la mitad del original
		for (int cont=1; cont < 10;cont++){
			ModelInstance cubo = new ModelInstance(modelCubo);
			if (cont>3 && cont<6){
				cubo.transform.setToTranslationAndScaling(cont*3, 0, 0, 0.5f, 0.5f, 0.5f);
			}
			else
				cubo.transform.setTranslation(cont*3,0,0);

			instances.add(cubo);

		}

	}


	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camController.update();

		pos+=delta * 1f;

		//Rotamos todos los cubos. Como ya están trasladados, la rotación es en el punto en el que se encuentran (como movimiento de rotacion de la tierra).
		//No LLamamos a setToTranslate ya que este inicializa la matriz y movería todos los cubos a (0,0,0)
		for (ModelInstance modelI : instances){
			modelI.transform.rotate(0,1,0,delta*30f);	// Continua a rotación, non empeza de 0
		}

		//Movemos nave
		instanceNave.transform.setTranslation(pos,0,0);

		//Hacemos que cámara siga a la nave
		camara3d.lookAt(pos,0,0);
		camara3d.update();

		modelBatch.begin(camara3d);

		//Renderizar el modelInstance
		modelBatch.render(instances,environment);
		modelBatch.render(instanceNave,environment);

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
		instances.clear();


	}

}
