
Ext.namespace("Hippo.grids");

Hippo.grids.FieldDefinitionFactory = (function () {
	var createdFieldDefs = {};
	
	return {
		createFieldDef : function(gridName) {
			if (createdFieldDefs[gridName]) {
				return createdFieldDefs[gridName];
			}
			
			var def = null;
			switch (gridName) {
			    case 'experimentGrid':
				    def = [{name:'uid', type:'int'}, {name:'title'}, {name:'purpose'}];
				    break;
				default:
					throw new Error(gridName + ' does not have a configured field definition');
			}
			createdFieldDefs[gridName] = def;
			return def;
		}
	};
	
})();