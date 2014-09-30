package ibicf;
import image.ImagesLoader;
import image.ImagesPlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Sprite 
{
  private static final int XSTEP = 5; 
  private static final int YSTEP = 5;
  protected static final int SIZE = 12;
  private ImagesLoader imsLoader;
  private String imageName;
  protected BufferedImage image;
  private BufferedImage imageBase;
  protected int width, height;     // image dimensions
  protected ImagesPlayer player;  // for playing a loop of images
  protected boolean isLooping;
  protected boolean isActive = true;      
  public int locx;        // location of sprite
  public int locy;
  protected int dx, dy;            // amount to move for each update
  private ArrayList<Coord> listDesPoints=new ArrayList<Coord>();
  
  public Sprite(int x, int y, String name) 
  { 
	listDesPoints=new ArrayList<Coord>();
    locx = x; locy = y;
    dx = XSTEP; dy = YSTEP;
    imsLoader = ThePanel.imsLoader;
    setImage(name);    // the sprite's default image is 'name'
    
  } 
  public Sprite(int x, int y, String name,ImagesLoader imsMenu) 
  { 
	listDesPoints=new ArrayList<Coord>();
    locx = x; locy = y;
    dx = XSTEP; dy = YSTEP;
    imsLoader = imsMenu;
    setImage(name);    // the sprite's default image is 'name'
    
  } 
  public BufferedImage getImageBase() {
	return imageBase;
}
  
  
  public void rotate(int angle) {

	  image=ThePanel.getImageSFXs().getRotatedImage(imageBase,angle);
//      width = imageBase.getWidth();
//      height = imageBase.getHeight();
  }

  public BufferedImage getImage() {
	return image;
}
  
  protected boolean grandeTaille() {
		if (imageBase.getWidth()==width && imageBase.getHeight()==height)
		{return true;}
			else
		{return false;}
  }
  
  protected boolean petiteTaille() {
		if (width==(imageBase.getWidth()/2) && height==(imageBase.getHeight()/2))
		{return true;}
			else
		{return false;}
}
  
  protected void agrandir(double pasAgrandi) {
	  if (width!=imageBase.getWidth())width+=pasAgrandi;
	  if (height!=imageBase.getHeight())height+=pasAgrandi;
  }
 
  protected void retrecir(double pasAgrandi) {
	  if (width!=imageBase.getWidth()/2)width-=pasAgrandi;
	  if (height!=imageBase.getHeight()/2)height-=pasAgrandi;
}
  protected void retrecir(int mlarg,int mHaut) {
	  width=mlarg;
	  height=mHaut;
  }
  
  protected void centrer() {
	  if ((getXPosn()+getWidth()/2)<ThePanel.xCentre)setStepProj(1,0);
	  if ((getXPosn()+getWidth()/2)>ThePanel.xCentre)setStepProj(-1,0);
	  if ((getYPosn()+getHeight()/2)<ThePanel.yCentre)setStepProj(0,1);
	  if ((getYPosn()+getHeight()/2)>ThePanel.yCentre)setStepProj(0,-1);
}
  
  public void setImage(String name)
  // assign the name image to the sprite
  {
    imageName = name;
    image = imsLoader.getImage(imageName);
    imageBase = image;
    if (image == null) {    // no image of that name was found
     // System.out.println("No sprite image for " + imageName);
      width = SIZE;
      height = SIZE;
    }
    else {
    	if (name=="mit" || name=="mit1" || name=="mit2")
    	{
    		width = image.getWidth();
    	     height = image.getHeight();
    	}
    	else
    	{
    		width = (int)((float)image.getWidth()*ThePanel.coeffResX);
        	height = (int)((float)image.getHeight()*ThePanel.coeffResY);
    	}
    	
      
    }
    // no image loop playing 
    player = null;
    isLooping = false;
  }  // end of setImage()


  public void loopImage(int animPeriod, double seqDuration)
  /* Switch on loop playing. The total time for the loop is
     seqDuration secs. The update interval (from the enclosing
     panel) is animPeriod ms. */
  {
    if (imsLoader.numImages(imageName) > 1) {
      player = null;   // to encourage garbage collection of previous player
      player = new ImagesPlayer(imageName, animPeriod, seqDuration,
                                       true, imsLoader);
      isLooping = true;
    }
    else
      System.out.println(imageName + " is not a sequence of images");
  }  // end of loopImage()


  public int getWidth()    // of the sprite's image
  {  return width;  }

  public int getHeight()   // of the sprite's image
  {  return height;  }

  public boolean isActive() 
  {  return isActive;}

  public void setActive(boolean a) 
  {  isActive = a;}

  public void setPosition(int x, int y)
  {  locx = x; locy = y; }

  public void translate(int xDist, int yDist)
  {  locx += xDist;  locy += yDist;  }

  public int getXPosn()
  {  return locx;  }

  public int getYPosn()
  {  return locy;  }


  public void setStep(int dx, int dy,boolean avionPrincipal)
  {  this.dx = dx; this.dy = dy; 
  	
	  if (avionPrincipal)
	  {
		  int decallageAiles=28;
		  if (locx + dx+width-decallageAiles<ThePanel.getInstance().getWidth() & locx + dx+decallageAiles>0 )locx += dx;
		  if (locy + dy+height<ThePanel.getInstance().getHeight() & locy + dy>0 )locy += dy; 
	  }
	  else
	  {
		  locx += dx;
		  locy += dy;
	  }
  	}
  public void setStepProj(int dx, int dy)
  { this.dx = dx; this.dy = dy; 
  	locx += dx;
  	locy += dy;
  	}

  public int getXStep()
  {  return dx;  }

  public int getYStep()
  {  return dy;  }


  public Rectangle getMyRectangle()
  {  return  new Rectangle(locx, locy, width, height);  }

  
  public void setMesPts() {
	  
	  Coord mesCoord;
	  boolean trouve=false;
	  int x=0;
	  int y=0;
	  int precision;
	  
	  if (getWidth()>4)
	  {
		  precision=getWidth()/5;
	  }
	  else
	  {
		  precision=1;
	  }
		  for (y = 1; y < getHeight(); y+=precision) 
		  {
			  trouve = false;
				for (x = 1; x < getWidth(); x++) 
				{
					if (image.getRGB(x,y)!=0)
					{
						if (trouve == false)
						{
							trouve = true;
							
							mesCoord=new Coord(x,y);
							listDesPoints.add(mesCoord);
							x = getWidth();
						}
					}
				}
		}
		  
		  y=getHeight()-1;
		  while (y>=0) {	 
			  trouve = false;
			  x=getWidth()-1;
			  while (x>0) {
				try {
					if (image.getRGB(x,y)!=0 && trouve == false)
					{
						trouve = true;
						mesCoord=new Coord(x,y);
						listDesPoints.add(mesCoord);
					}
				} catch (Exception e) {
					
				}
				x-=1;
			  }
			  y-=precision;
		  } 
}
  
  protected void setTaille(int larg,int haut) {
	width=larg;
	height=haut;
}
  
  public Polygon getMyPolygon()
  {  
	  Polygon poly= new Polygon();	  
	  
	  for (int i = 0; i < listDesPoints.size(); i++) {
		poly.addPoint(listDesPoints.get(i).getXcoord()+locx,listDesPoints.get(i).getYcoord()+locy);
	}
	  
	  return  poly;
 }

  
  public void drawSprite(Graphics g) 
  {
    if (isActive()) {
      if (image == null) {   // the sprite has no image
        g.setColor(Color.yellow);   // draw a yellow circle instead
        g.fillOval(locx, locy, SIZE, SIZE);
        g.setColor(Color.black);
      }
      else {
        if (isLooping)
        image = player.getCurrentImage();
       // System.out.println(ThePanel.coeffResX);
        g.drawImage(image, locx, locy,width,height, null);
        
      }
    }
  }
  
  @Override
public void finalize() throws Throwable {
	  //if (ThePanel.listeProj!=null)  System.out.println("F:"+ThePanel.listeProj.size());
	super.finalize();
}
}
