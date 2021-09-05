package entity.geometry;

/**
 * Interface para funções de transformação
 * @author gustavolr
 *
 */
public interface Transformation2D {

	public void translation(int x, int y);
	public void rotation(int angle);
	public void reflexion(int x, int y);
	public void scaling(float scalex, float scaley);
}
