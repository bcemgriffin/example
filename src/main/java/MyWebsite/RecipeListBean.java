package MyWebsite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeListBean implements Serializable {
		/**
		 * 
		 */
	    public ArrayList<RecipeBean> recipelist;
		private static final long serialVersionUID = 1L;
		
		public RecipeListBean() {
			this.recipelist = new ArrayList<RecipeBean>();
		}
		public ArrayList getList() {
			return recipelist;
		}
}
