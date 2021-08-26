package scene;

/**
 * Classe para gerenciar os possiveis estados
 * que uma cena pode estar no programa
 * @author gustavolr <p>
 *
 * Valores possiveis atualmente: <p>
 * <strong>InBackground</strong>: A cena esta rodando em fundo, logo, apenas a sua funcao render
 * sera executada, o tick nao sera atualizado <p>
 * <strong>Running</strong>: Esta eh a cena primaria, as funcoes render e tick
 * sao executadas <p>
 * <strong>Stopped</strong>: Esta cena esta parada, suas funcoes render e tick nao serao executadas <p>
 */
public enum SceneState {
	
	Stopped      (-1),
	InBackground (0),
	Running      (1),
	UI           (2);

	private int scene_code;
	
	SceneState(int scene_code) {
		this.scene_code = scene_code;
	}
	
	public int get_scene_code() {
		return this.scene_code;
	}
	
	
}
