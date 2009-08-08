
Ext.namespace('Ext.action');

// Create MyComponent which is a pre-configured version of Ext.some.component
Ext.action.NewExperimentAction = Ext.extend(Ext.form.Action, {
	method: 'post'
	,url: "/hippo/experiments"
	,success: function() {}
	,failure: function() {}
});
 
// register xtype to allow for lazy initialization
Ext.reg('newexperimentaction', Ext.action.NewExperimentAction);
