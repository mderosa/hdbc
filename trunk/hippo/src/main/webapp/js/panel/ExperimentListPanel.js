
Ext.namespace('Hippo.panel');

Hippo.panel.ExperimentListPanel = Ext.extend(Ext.grid.GridPanel, {
	
	initComponent: function() {
		Ext.apply(this, {
			id: 'expList'
			,columns: [
				{header: 'Uid', dataIndex: 'uid', hidden: true},
				{header: 'Title', dataIndex: 'title', menuDisabled: true},
				{header: 'Purpose', dataIndex: 'purpose', menuDisabled: true}
			]
			,store: new Ext.data.JsonStore({
				autoDestroy: true,
				root: 'rows',
				idProperty: 'uid',
				fields: [{name:'uid', type:'int'}, {name:'title'}, {name:'purpose'}],
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
				
		Hippo.panel.ExperimentListPanel.superclass.initComponent.apply(this, arguments);		
	}

});

Ext.reg('experimentlistpanel', Hippo.panel.ExperimentListPanel);