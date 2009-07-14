
Ext.namespace('Hippo');

Hippo.ExperimentListPanel = Ext.extend(Ext.grid.GridPanel, {
	
	initComponent: function() {
		Ext.apply(this, {
			columns: [
				{header: 'Title', dataIndex: 'title'},
				{header: 'Purpose', dataIndex: 'purpose'}
			]
			,store: new Ext.data.Store({
				data: [
					['exp title1', 'exp purpose1']
					,['exp title2', 'exp purpose2']
				]
				,reader: new Ext.data.ArrayReader({}, [
					{name: 'title'}
					,{name: 'purpose'}
				])
			})
			,autoHeight: true
			,viewConfig: {
				forceFit: true
			}
		});
				
		Hippo.ExperimentListPanel.superclass.initComponent.apply(this, arguments);		
	}

});

Ext.reg('experimentlistpanel', Hippo.ExperimentListPanel);