package entities;

import animation.Humanoid;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

    private Vector3f position = new Vector3f(0,0,0);
    private float pitch = 20;
    private float yaw;

    private float roll;
    public float distanceFromPlayer = 50;

    public float angleAroundPlayer = 0;

    public Humanoid player;

    public Camera(Humanoid player) {
        this.player = player;
    }

    public Camera() {}

    public void move() {
        calculateZoom();
        calculatePitch();
        calculateAngleAroundPlayer();
        float horizontalDistance = calculateHorizontalDistance();
        float verticalDistance = calculateVerticalDistance();
        calculateCameraPosition(horizontalDistance,verticalDistance);
        this.yaw = 180 - (player.rot_y + angleAroundPlayer);
    }

    public void move2() {
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			position.z-=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			position.z+=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			position.x+=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			position.x-=0.2f;
		}
        if(Keyboard.isKeyDown(Keyboard.KEY_L)){
            position.y+=0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_M)){
            position.y-=0.2f;
        }
    }

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	public void calculateZoom() {
	    float zoomLevel = Mouse.getDWheel() * 0.1f;
	    distanceFromPlayer -= zoomLevel;
    }

    public void calculatePitch() {
	    if (Mouse.isButtonDown(1)) {
	        float pitchChange = Mouse.getDY() * 0.1f;
	        pitch -= pitchChange;
        }
    }

    public void calculateAngleAroundPlayer() {
        if (Mouse.isButtonDown(0)) {
            float AAPChange = Mouse.getDX() * 0.3f;
            angleAroundPlayer -= AAPChange;
        }
    }

    public float calculateHorizontalDistance() {
	    return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
    }

    public float calculateVerticalDistance() {
        return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
    }

    public void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
        float theta = player.rot_y + angleAroundPlayer;
        float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(theta)));
        position.x = player.pos_x - offsetX;
        position.z = player.pos_z - offsetZ;
        position.y = player.pos_y + verticalDistance;
    }
}
