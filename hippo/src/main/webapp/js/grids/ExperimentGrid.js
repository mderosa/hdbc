
Ext.namespace('Hippo.grids');

Hippo.grids.ExperimentGrid = Ext.extend(Ext.grid.GridPanel, {
	
	addNewExperiment: function(data) {
		var gridRecord = Ext.data.Record.create(
				Hippo.grids.FieldDefinitionFactory.createFieldDef('experimentGrid')
			);
		var newdata = new gridRecord({uid: data.uid, title: data.title, purpose: data.purpose});
		this.store.add(newdata);
	},
	
	initComponent: function() {
		var name = 'experimentGrid';
		Ext.apply(this, {
			id: name
			,columns: [
				{header: 'Uid', dataIndex: 'uid', hidden: true},
				{header: 'Title', dataIndex: 'title', menuDisabled: true},
				{header: 'Purpose', dataIndex: 'purpose',  menuDisabled: true}
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
		
		this.relayEvents(Ext.getCmp('viewport'), ['newexperimentsaved']);		
		this.on('on_newExperimentSaved', function(rsp, dlg) {
				this.addNewExperiment(rsp.experiment);
				dlg.destroy();
			}, this);
		this.on('rowclick', function(grid, rowIndex, evntObj) {
				var uid = grid.getStore().getAt(rowIndex).get('uid');
				Ext.getCmp('viewport').fireEvent('on_experimentSelected', uid)
				console.log(uid);
			}, this);
			
	},
	
	onRender: function() {
		if (this.store.url) {
			this.store.load();
		}
		Hippo.grids.ExperimentGrid.superclass.onRender.apply(this, arguments);
	}

});

Ext.reg('grids.ExperimentGrid', Hippo.grids.ExperimentGrid);
