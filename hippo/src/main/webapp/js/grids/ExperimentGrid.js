
Ext.namespace('Hippo.grids');

Hippo.grids.ExperimentGrid = Ext.extend(Ext.grid.GridPanel, {
	
	initComponent: function() {
		var name = 'experimentGrid';
		Ext.apply(this, {
			id: name
			,columns: [
				{header: 'Uid', dataIndex: 'uid', hidden: true},
				{header: 'Title', dataIndex: 'title', menuDisabled: true},
				{header: 'Purpose', dataIndex: 'purpose', menuDisabled: true}
			]
			,store: new Ext.data.JsonStore({
				autoDestroy: true,
				root: 'data',
				idProperty: 'uid',
				fields: Hippo.grids.FieldDefinitionFactory.createFieldDef(name),
				url: 'console/experiments/lists/active' 
			})
			,autoScroll: true
			,autoHeight: true
			,viewConfig: {
				forceFit: true
			}
		});
				
		Hippo.grids.ExperimentGrid.superclass.initComponent.apply(this, arguments);		
	},
	
	onRender: function() {
		if (this.store.url) {
			this.store.load();
		}
		Hippo.grids.ExperimentGrid.superclass.onRender.apply(this, arguments);
	}

});

Ext.reg('experimentGrid', Hippo.grids.ExperimentGrid);
