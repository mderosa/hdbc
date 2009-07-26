
Ext.namespace('Hippo.panel');

Hippo.panel.ExperimentListPanel = Ext.extend(Ext.grid.GridPanel, {
	
	initComponent: function() {
		Ext.apply(this, {
			id: 'expList'
			,columns: [
				{dataIndex: 'uid', hidden: true},
				{header: 'Title', dataIndex: 'title', menuDisabled: true},
				{header: 'Purpose', dataIndex: 'purpose', menuDisabled: true}
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
			,autoScroll: true
			,autoHeight: true
			,viewConfig: {
				forceFit: true
			}
		});
				
		Hippo.panel.ExperimentListPanel.superclass.initComponent.apply(this, arguments);		
	}

});

Ext.reg('experimentlistpanel', Hippo.panel.ExperimentListPanel);