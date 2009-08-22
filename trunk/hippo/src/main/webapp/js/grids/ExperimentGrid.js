
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
				root: 'rows',
				idProperty: 'uid',
				fields: Hippo.grids.FieldDefinitionFactory.createFieldDef(name),
				data: {rows: [
					{uid:1, title:'expa title1', purpose:'exp purpose1'},
					{uid:2, title:'exp title2', purpose:'exp purpose2'}
				]}
			})
			,autoScroll: true
			,autoHeight: true
			,viewConfig: {
				forceFit: true
			}
		});
				
		Hippo.grids.ExperimentGrid.superclass.initComponent.apply(this, arguments);		
	}

});

Ext.reg('experimentGrid', Hippo.grids.ExperimentGrid);
