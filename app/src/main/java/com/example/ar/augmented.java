package com.example.ar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class augmented extends AppCompatActivity {
    private ArFragment arFragment;
    private ModelRenderable modelRenderable;
    //3d model credit : google.poly.com
    private String Model_URL = "https://modelviewer.dev/shared-assets/models/Astronaut.glb";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_augmented);
        Intent intent = getIntent();
        Model_URL = intent.getStringExtra("model_url");
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        System.out.println(arFragment);
        setUpModel();
        setUpPlane();
    }

    //To setup 3D model
    private void setUpModel() {
        ModelRenderable.builder()
                .setSource(this,
                        RenderableSource.builder().setSource(
                                this,
                                Uri.parse(Model_URL),
                                RenderableSource.SourceType.GLB)
                                .setScale(0.75f)
                                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                                .build())

                .setRegistryId(Model_URL)



                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(throwable -> {
                    Log.i("Model","cant load");
                    Toast.makeText(augmented.this,"Model can't be Loaded", Toast.LENGTH_SHORT).show();
                    return null;
                });
    }

    //To setup the plane
    private void setUpPlane(){
            arFragment.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(arFragment.getArSceneView().getScene());
            createModel(anchorNode);
        }));
    }

    //To create 3D model from the link
    private void createModel(AnchorNode anchorNode){
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        node.select();
    }
}
