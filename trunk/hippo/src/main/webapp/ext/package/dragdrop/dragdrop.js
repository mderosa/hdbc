/*
 * Ext JS Library 3.0 RC2
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */


(function(){var Event=Ext.EventManager;var Dom=Ext.lib.Dom;Ext.dd.DragDrop=function(id,sGroup,config){if(id){this.init(id,sGroup,config);}};Ext.dd.DragDrop.prototype={id:null,config:null,dragElId:null,handleElId:null,invalidHandleTypes:null,invalidHandleIds:null,invalidHandleClasses:null,startPageX:0,startPageY:0,groups:null,locked:false,lock:function(){this.locked=true;},moveOnly:false,unlock:function(){this.locked=false;},isTarget:true,padding:null,_domRef:null,__ygDragDrop:true,constrainX:false,constrainY:false,minX:0,maxX:0,minY:0,maxY:0,maintainOffset:false,xTicks:null,yTicks:null,primaryButtonOnly:true,available:false,hasOuterHandles:false,b4StartDrag:function(x,y){},startDrag:function(x,y){},b4Drag:function(e){},onDrag:function(e){},onDragEnter:function(e,id){},b4DragOver:function(e){},onDragOver:function(e,id){},b4DragOut:function(e){},onDragOut:function(e,id){},b4DragDrop:function(e){},onDragDrop:function(e,id){},onInvalidDrop:function(e){},b4EndDrag:function(e){},endDrag:function(e){},b4MouseDown:function(e){},onMouseDown:function(e){},onMouseUp:function(e){},onAvailable:function(){},defaultPadding:{left:0,right:0,top:0,bottom:0},constrainTo:function(constrainTo,pad,inContent){if(typeof pad=="number"){pad={left:pad,right:pad,top:pad,bottom:pad};}
pad=pad||this.defaultPadding;var b=Ext.get(this.getEl()).getBox();var ce=Ext.get(constrainTo);var s=ce.getScroll();var c,cd=ce.dom;if(cd==document.body){c={x:s.left,y:s.top,width:Ext.lib.Dom.getViewWidth(),height:Ext.lib.Dom.getViewHeight()};}else{var xy=ce.getXY();c={x:xy[0]+s.left,y:xy[1]+s.top,width:cd.clientWidth,height:cd.clientHeight};}
var topSpace=b.y-c.y;var leftSpace=b.x-c.x;this.resetConstraints();this.setXConstraint(leftSpace-(pad.left||0),c.width-leftSpace-b.width-(pad.right||0),this.xTickSize);this.setYConstraint(topSpace-(pad.top||0),c.height-topSpace-b.height-(pad.bottom||0),this.yTickSize);},getEl:function(){if(!this._domRef){this._domRef=Ext.getDom(this.id);}
return this._domRef;},getDragEl:function(){return Ext.getDom(this.dragElId);},init:function(id,sGroup,config){this.initTarget(id,sGroup,config);Event.on(this.id,"mousedown",this.handleMouseDown,this);},initTarget:function(id,sGroup,config){this.config=config||{};this.DDM=Ext.dd.DDM;this.groups={};if(typeof id!=="string"){id=Ext.id(id);}
this.id=id;this.addToGroup((sGroup)?sGroup:"default");this.handleElId=id;this.setDragElId(id);this.invalidHandleTypes={A:"A"};this.invalidHandleIds={};this.invalidHandleClasses=[];this.applyConfig();this.handleOnAvailable();},applyConfig:function(){this.padding=this.config.padding||[0,0,0,0];this.isTarget=(this.config.isTarget!==false);this.maintainOffset=(this.config.maintainOffset);this.primaryButtonOnly=(this.config.primaryButtonOnly!==false);},handleOnAvailable:function(){this.available=true;this.resetConstraints();this.onAvailable();},setPadding:function(iTop,iRight,iBot,iLeft){if(!iRight&&0!==iRight){this.padding=[iTop,iTop,iTop,iTop];}else if(!iBot&&0!==iBot){this.padding=[iTop,iRight,iTop,iRight];}else{this.padding=[iTop,iRight,iBot,iLeft];}},setInitPosition:function(diffX,diffY){var el=this.getEl();if(!this.DDM.verifyEl(el)){return;}
var dx=diffX||0;var dy=diffY||0;var p=Dom.getXY(el);this.initPageX=p[0]-dx;this.initPageY=p[1]-dy;this.lastPageX=p[0];this.lastPageY=p[1];this.setStartPosition(p);},setStartPosition:function(pos){var p=pos||Dom.getXY(this.getEl());this.deltaSetXY=null;this.startPageX=p[0];this.startPageY=p[1];},addToGroup:function(sGroup){this.groups[sGroup]=true;this.DDM.regDragDrop(this,sGroup);},removeFromGroup:function(sGroup){if(this.groups[sGroup]){delete this.groups[sGroup];}
this.DDM.removeDDFromGroup(this,sGroup);},setDragElId:function(id){this.dragElId=id;},setHandleElId:function(id){if(typeof id!=="string"){id=Ext.id(id);}
this.handleElId=id;this.DDM.regHandle(this.id,id);},setOuterHandleElId:function(id){if(typeof id!=="string"){id=Ext.id(id);}
Event.on(id,"mousedown",this.handleMouseDown,this);this.setHandleElId(id);this.hasOuterHandles=true;},unreg:function(){Event.un(this.id,"mousedown",this.handleMouseDown);this._domRef=null;this.DDM._remove(this);},destroy:function(){this.unreg();},isLocked:function(){return(this.DDM.isLocked()||this.locked);},handleMouseDown:function(e,oDD){if(this.primaryButtonOnly&&e.button!=0){return;}
if(this.isLocked()){return;}
this.DDM.refreshCache(this.groups);var pt=new Ext.lib.Point(Ext.lib.Event.getPageX(e),Ext.lib.Event.getPageY(e));if(!this.hasOuterHandles&&!this.DDM.isOverTarget(pt,this)){}else{if(this.clickValidator(e)){this.setStartPosition();this.b4MouseDown(e);this.onMouseDown(e);this.DDM.handleMouseDown(e,this);this.DDM.stopEvent(e);}else{}}},clickValidator:function(e){var target=e.getTarget();return(this.isValidHandleChild(target)&&(this.id==this.handleElId||this.DDM.handleWasClicked(target,this.id)));},addInvalidHandleType:function(tagName){var type=tagName.toUpperCase();this.invalidHandleTypes[type]=type;},addInvalidHandleId:function(id){if(typeof id!=="string"){id=Ext.id(id);}
this.invalidHandleIds[id]=id;},addInvalidHandleClass:function(cssClass){this.invalidHandleClasses.push(cssClass);},removeInvalidHandleType:function(tagName){var type=tagName.toUpperCase();delete this.invalidHandleTypes[type];},removeInvalidHandleId:function(id){if(typeof id!=="string"){id=Ext.id(id);}
delete this.invalidHandleIds[id];},removeInvalidHandleClass:function(cssClass){for(var i=0,len=this.invalidHandleClasses.length;i<len;++i){if(this.invalidHandleClasses[i]==cssClass){delete this.invalidHandleClasses[i];}}},isValidHandleChild:function(node){var valid=true;var nodeName;try{nodeName=node.nodeName.toUpperCase();}catch(e){nodeName=node.nodeName;}
valid=valid&&!this.invalidHandleTypes[nodeName];valid=valid&&!this.invalidHandleIds[node.id];for(var i=0,len=this.invalidHandleClasses.length;valid&&i<len;++i){valid=!Ext.fly(node).hasClass(this.invalidHandleClasses[i]);}
return valid;},setXTicks:function(iStartX,iTickSize){this.xTicks=[];this.xTickSize=iTickSize;var tickMap={};for(var i=this.initPageX;i>=this.minX;i=i-iTickSize){if(!tickMap[i]){this.xTicks[this.xTicks.length]=i;tickMap[i]=true;}}
for(i=this.initPageX;i<=this.maxX;i=i+iTickSize){if(!tickMap[i]){this.xTicks[this.xTicks.length]=i;tickMap[i]=true;}}
this.xTicks.sort(this.DDM.numericSort);},setYTicks:function(iStartY,iTickSize){this.yTicks=[];this.yTickSize=iTickSize;var tickMap={};for(var i=this.initPageY;i>=this.minY;i=i-iTickSize){if(!tickMap[i]){this.yTicks[this.yTicks.length]=i;tickMap[i]=true;}}
for(i=this.initPageY;i<=this.maxY;i=i+iTickSize){if(!tickMap[i]){this.yTicks[this.yTicks.length]=i;tickMap[i]=true;}}
this.yTicks.sort(this.DDM.numericSort);},setXConstraint:function(iLeft,iRight,iTickSize){this.leftConstraint=iLeft;this.rightConstraint=iRight;this.minX=this.initPageX-iLeft;this.maxX=this.initPageX+iRight;if(iTickSize){this.setXTicks(this.initPageX,iTickSize);}
this.constrainX=true;},clearConstraints:function(){this.constrainX=false;this.constrainY=false;this.clearTicks();},clearTicks:function(){this.xTicks=null;this.yTicks=null;this.xTickSize=0;this.yTickSize=0;},setYConstraint:function(iUp,iDown,iTickSize){this.topConstraint=iUp;this.bottomConstraint=iDown;this.minY=this.initPageY-iUp;this.maxY=this.initPageY+iDown;if(iTickSize){this.setYTicks(this.initPageY,iTickSize);}
this.constrainY=true;},resetConstraints:function(){if(this.initPageX||this.initPageX===0){var dx=(this.maintainOffset)?this.lastPageX-this.initPageX:0;var dy=(this.maintainOffset)?this.lastPageY-this.initPageY:0;this.setInitPosition(dx,dy);}else{this.setInitPosition();}
if(this.constrainX){this.setXConstraint(this.leftConstraint,this.rightConstraint,this.xTickSize);}
if(this.constrainY){this.setYConstraint(this.topConstraint,this.bottomConstraint,this.yTickSize);}},getTick:function(val,tickArray){if(!tickArray){return val;}else if(tickArray[0]>=val){return tickArray[0];}else{for(var i=0,len=tickArray.length;i<len;++i){var next=i+1;if(tickArray[next]&&tickArray[next]>=val){var diff1=val-tickArray[i];var diff2=tickArray[next]-val;return(diff2>diff1)?tickArray[i]:tickArray[next];}}
return tickArray[tickArray.length-1];}},toString:function(){return("DragDrop "+this.id);}};})();if(!Ext.dd.DragDropMgr){Ext.dd.DragDropMgr=function(){var Event=Ext.EventManager;return{ids:{},handleIds:{},dragCurrent:null,dragOvers:{},deltaX:0,deltaY:0,preventDefault:true,stopPropagation:true,initialized:false,locked:false,init:function(){this.initialized=true;},POINT:0,INTERSECT:1,mode:0,_execOnAll:function(sMethod,args){for(var i in this.ids){for(var j in this.ids[i]){var oDD=this.ids[i][j];if(!this.isTypeOfDD(oDD)){continue;}
oDD[sMethod].apply(oDD,args);}}},_onLoad:function(){this.init();Event.on(document,"mouseup",this.handleMouseUp,this,true);Event.on(document,"mousemove",this.handleMouseMove,this,true);Event.on(window,"unload",this._onUnload,this,true);Event.on(window,"resize",this._onResize,this,true);},_onResize:function(e){this._execOnAll("resetConstraints",[]);},lock:function(){this.locked=true;},unlock:function(){this.locked=false;},isLocked:function(){return this.locked;},locationCache:{},useCache:true,clickPixelThresh:3,clickTimeThresh:350,dragThreshMet:false,clickTimeout:null,startX:0,startY:0,regDragDrop:function(oDD,sGroup){if(!this.initialized){this.init();}
if(!this.ids[sGroup]){this.ids[sGroup]={};}
this.ids[sGroup][oDD.id]=oDD;},removeDDFromGroup:function(oDD,sGroup){if(!this.ids[sGroup]){this.ids[sGroup]={};}
var obj=this.ids[sGroup];if(obj&&obj[oDD.id]){delete obj[oDD.id];}},_remove:function(oDD){for(var g in oDD.groups){if(g&&this.ids[g]&&this.ids[g][oDD.id]){delete this.ids[g][oDD.id];}}
delete this.handleIds[oDD.id];},regHandle:function(sDDId,sHandleId){if(!this.handleIds[sDDId]){this.handleIds[sDDId]={};}
this.handleIds[sDDId][sHandleId]=sHandleId;},isDragDrop:function(id){return(this.getDDById(id))?true:false;},getRelated:function(p_oDD,bTargetsOnly){var oDDs=[];for(var i in p_oDD.groups){for(var j in this.ids[i]){var dd=this.ids[i][j];if(!this.isTypeOfDD(dd)){continue;}
if(!bTargetsOnly||dd.isTarget){oDDs[oDDs.length]=dd;}}}
return oDDs;},isLegalTarget:function(oDD,oTargetDD){var targets=this.getRelated(oDD,true);for(var i=0,len=targets.length;i<len;++i){if(targets[i].id==oTargetDD.id){return true;}}
return false;},isTypeOfDD:function(oDD){return(oDD&&oDD.__ygDragDrop);},isHandle:function(sDDId,sHandleId){return(this.handleIds[sDDId]&&this.handleIds[sDDId][sHandleId]);},getDDById:function(id){for(var i in this.ids){if(this.ids[i][id]){return this.ids[i][id];}}
return null;},handleMouseDown:function(e,oDD){if(Ext.QuickTips){Ext.QuickTips.disable();}
if(this.dragCurrent){this.handleMouseUp(e);}
this.currentTarget=e.getTarget();this.dragCurrent=oDD;var el=oDD.getEl();this.startX=e.getPageX();this.startY=e.getPageY();this.deltaX=this.startX-el.offsetLeft;this.deltaY=this.startY-el.offsetTop;this.dragThreshMet=false;this.clickTimeout=setTimeout(function(){var DDM=Ext.dd.DDM;DDM.startDrag(DDM.startX,DDM.startY);},this.clickTimeThresh);},startDrag:function(x,y){clearTimeout(this.clickTimeout);if(this.dragCurrent){this.dragCurrent.b4StartDrag(x,y);this.dragCurrent.startDrag(x,y);}
this.dragThreshMet=true;},handleMouseUp:function(e){if(Ext.QuickTips){Ext.QuickTips.enable();}
if(!this.dragCurrent){return;}
clearTimeout(this.clickTimeout);if(this.dragThreshMet){this.fireEvents(e,true);}else{}
this.stopDrag(e);this.stopEvent(e);},stopEvent:function(e){if(this.stopPropagation){e.stopPropagation();}
if(this.preventDefault){e.preventDefault();}},stopDrag:function(e){if(this.dragCurrent){if(this.dragThreshMet){this.dragCurrent.b4EndDrag(e);this.dragCurrent.endDrag(e);}
this.dragCurrent.onMouseUp(e);}
this.dragCurrent=null;this.dragOvers={};},handleMouseMove:function(e){if(!this.dragCurrent){return true;}
if(Ext.isIE&&(e.button!==0&&e.button!==1&&e.button!==2)){this.stopEvent(e);return this.handleMouseUp(e);}
if(!this.dragThreshMet){var diffX=Math.abs(this.startX-e.getPageX());var diffY=Math.abs(this.startY-e.getPageY());if(diffX>this.clickPixelThresh||diffY>this.clickPixelThresh){this.startDrag(this.startX,this.startY);}}
if(this.dragThreshMet){this.dragCurrent.b4Drag(e);this.dragCurrent.onDrag(e);if(!this.dragCurrent.moveOnly){this.fireEvents(e,false);}}
this.stopEvent(e);return true;},fireEvents:function(e,isDrop){var dc=this.dragCurrent;if(!dc||dc.isLocked()){return;}
var pt=e.getPoint();var oldOvers=[];var outEvts=[];var overEvts=[];var dropEvts=[];var enterEvts=[];for(var i in this.dragOvers){var ddo=this.dragOvers[i];if(!this.isTypeOfDD(ddo)){continue;}
if(!this.isOverTarget(pt,ddo,this.mode)){outEvts.push(ddo);}
oldOvers[i]=true;delete this.dragOvers[i];}
for(var sGroup in dc.groups){if("string"!=typeof sGroup){continue;}
for(i in this.ids[sGroup]){var oDD=this.ids[sGroup][i];if(!this.isTypeOfDD(oDD)){continue;}
if(oDD.isTarget&&!oDD.isLocked()&&((oDD!=dc)||(dc.ignoreSelf===false))){if(this.isOverTarget(pt,oDD,this.mode)){if(isDrop){dropEvts.push(oDD);}else{if(!oldOvers[oDD.id]){enterEvts.push(oDD);}else{overEvts.push(oDD);}
this.dragOvers[oDD.id]=oDD;}}}}}
if(this.mode){if(outEvts.length){dc.b4DragOut(e,outEvts);dc.onDragOut(e,outEvts);}
if(enterEvts.length){dc.onDragEnter(e,enterEvts);}
if(overEvts.length){dc.b4DragOver(e,overEvts);dc.onDragOver(e,overEvts);}
if(dropEvts.length){dc.b4DragDrop(e,dropEvts);dc.onDragDrop(e,dropEvts);}}else{var len=0;for(i=0,len=outEvts.length;i<len;++i){dc.b4DragOut(e,outEvts[i].id);dc.onDragOut(e,outEvts[i].id);}
for(i=0,len=enterEvts.length;i<len;++i){dc.onDragEnter(e,enterEvts[i].id);}
for(i=0,len=overEvts.length;i<len;++i){dc.b4DragOver(e,overEvts[i].id);dc.onDragOver(e,overEvts[i].id);}
for(i=0,len=dropEvts.length;i<len;++i){dc.b4DragDrop(e,dropEvts[i].id);dc.onDragDrop(e,dropEvts[i].id);}}
if(isDrop&&!dropEvts.length){dc.onInvalidDrop(e);}},getBestMatch:function(dds){var winner=null;var len=dds.length;if(len==1){winner=dds[0];}else{for(var i=0;i<len;++i){var dd=dds[i];if(dd.cursorIsOver){winner=dd;break;}else{if(!winner||winner.overlap.getArea()<dd.overlap.getArea()){winner=dd;}}}}
return winner;},refreshCache:function(groups){for(var sGroup in groups){if("string"!=typeof sGroup){continue;}
for(var i in this.ids[sGroup]){var oDD=this.ids[sGroup][i];if(this.isTypeOfDD(oDD)){var loc=this.getLocation(oDD);if(loc){this.locationCache[oDD.id]=loc;}else{delete this.locationCache[oDD.id];}}}}},verifyEl:function(el){if(el){var parent;if(Ext.isIE){try{parent=el.offsetParent;}catch(e){}}else{parent=el.offsetParent;}
if(parent){return true;}}
return false;},getLocation:function(oDD){if(!this.isTypeOfDD(oDD)){return null;}
var el=oDD.getEl(),pos,x1,x2,y1,y2,t,r,b,l;try{pos=Ext.lib.Dom.getXY(el);}catch(e){}
if(!pos){return null;}
x1=pos[0];x2=x1+el.offsetWidth;y1=pos[1];y2=y1+el.offsetHeight;t=y1-oDD.padding[0];r=x2+oDD.padding[1];b=y2+oDD.padding[2];l=x1-oDD.padding[3];return new Ext.lib.Region(t,r,b,l);},isOverTarget:function(pt,oTarget,intersect){var loc=this.locationCache[oTarget.id];if(!loc||!this.useCache){loc=this.getLocation(oTarget);this.locationCache[oTarget.id]=loc;}
if(!loc){return false;}
oTarget.cursorIsOver=loc.contains(pt);var dc=this.dragCurrent;if(!dc||!dc.getTargetCoord||(!intersect&&!dc.constrainX&&!dc.constrainY)){return oTarget.cursorIsOver;}
oTarget.overlap=null;var pos=dc.getTargetCoord(pt.x,pt.y);var el=dc.getDragEl();var curRegion=new Ext.lib.Region(pos.y,pos.x+el.offsetWidth,pos.y+el.offsetHeight,pos.x);var overlap=curRegion.intersect(loc);if(overlap){oTarget.overlap=overlap;return(intersect)?true:oTarget.cursorIsOver;}else{return false;}},_onUnload:function(e,me){Ext.dd.DragDropMgr.unregAll();},unregAll:function(){if(this.dragCurrent){this.stopDrag();this.dragCurrent=null;}
this._execOnAll("unreg",[]);for(var i in this.elementCache){delete this.elementCache[i];}
this.elementCache={};this.ids={};},elementCache:{},getElWrapper:function(id){var oWrapper=this.elementCache[id];if(!oWrapper||!oWrapper.el){oWrapper=this.elementCache[id]=new this.ElementWrapper(Ext.getDom(id));}
return oWrapper;},getElement:function(id){return Ext.getDom(id);},getCss:function(id){var el=Ext.getDom(id);return(el)?el.style:null;},ElementWrapper:function(el){this.el=el||null;this.id=this.el&&el.id;this.css=this.el&&el.style;},getPosX:function(el){return Ext.lib.Dom.getX(el);},getPosY:function(el){return Ext.lib.Dom.getY(el);},swapNode:function(n1,n2){if(n1.swapNode){n1.swapNode(n2);}else{var p=n2.parentNode;var s=n2.nextSibling;if(s==n1){p.insertBefore(n1,n2);}else if(n2==n1.nextSibling){p.insertBefore(n2,n1);}else{n1.parentNode.replaceChild(n2,n1);p.insertBefore(n1,s);}}},getScroll:function(){var t,l,dde=document.documentElement,db=document.body;if(dde&&(dde.scrollTop||dde.scrollLeft)){t=dde.scrollTop;l=dde.scrollLeft;}else if(db){t=db.scrollTop;l=db.scrollLeft;}else{}
return{top:t,left:l};},getStyle:function(el,styleProp){return Ext.fly(el).getStyle(styleProp);},getScrollTop:function(){return this.getScroll().top;},getScrollLeft:function(){return this.getScroll().left;},moveToEl:function(moveEl,targetEl){var aCoord=Ext.lib.Dom.getXY(targetEl);Ext.lib.Dom.setXY(moveEl,aCoord);},numericSort:function(a,b){return(a-b);},_timeoutCount:0,_addListeners:function(){var DDM=Ext.dd.DDM;if(Ext.lib.Event&&document){DDM._onLoad();}else{if(DDM._timeoutCount>2000){}else{setTimeout(DDM._addListeners,10);if(document&&document.body){DDM._timeoutCount+=1;}}}},handleWasClicked:function(node,id){if(this.isHandle(id,node.id)){return true;}else{var p=node.parentNode;while(p){if(this.isHandle(id,p.id)){return true;}else{p=p.parentNode;}}}
return false;}};}();Ext.dd.DDM=Ext.dd.DragDropMgr;Ext.dd.DDM._addListeners();}
Ext.dd.DD=function(id,sGroup,config){if(id){this.init(id,sGroup,config);}};Ext.extend(Ext.dd.DD,Ext.dd.DragDrop,{scroll:true,autoOffset:function(iPageX,iPageY){var x=iPageX-this.startPageX;var y=iPageY-this.startPageY;this.setDelta(x,y);},setDelta:function(iDeltaX,iDeltaY){this.deltaX=iDeltaX;this.deltaY=iDeltaY;},setDragElPos:function(iPageX,iPageY){var el=this.getDragEl();this.alignElWithMouse(el,iPageX,iPageY);},alignElWithMouse:function(el,iPageX,iPageY){var oCoord=this.getTargetCoord(iPageX,iPageY);var fly=el.dom?el:Ext.fly(el,'_dd');if(!this.deltaSetXY){var aCoord=[oCoord.x,oCoord.y];fly.setXY(aCoord);var newLeft=fly.getLeft(true);var newTop=fly.getTop(true);this.deltaSetXY=[newLeft-oCoord.x,newTop-oCoord.y];}else{fly.setLeftTop(oCoord.x+this.deltaSetXY[0],oCoord.y+this.deltaSetXY[1]);}
this.cachePosition(oCoord.x,oCoord.y);this.autoScroll(oCoord.x,oCoord.y,el.offsetHeight,el.offsetWidth);return oCoord;},cachePosition:function(iPageX,iPageY){if(iPageX){this.lastPageX=iPageX;this.lastPageY=iPageY;}else{var aCoord=Ext.lib.Dom.getXY(this.getEl());this.lastPageX=aCoord[0];this.lastPageY=aCoord[1];}},autoScroll:function(x,y,h,w){if(this.scroll){var clientH=Ext.lib.Dom.getViewHeight();var clientW=Ext.lib.Dom.getViewWidth();var st=this.DDM.getScrollTop();var sl=this.DDM.getScrollLeft();var bot=h+y;var right=w+x;var toBot=(clientH+st-y-this.deltaY);var toRight=(clientW+sl-x-this.deltaX);var thresh=40;var scrAmt=(document.all)?80:30;if(bot>clientH&&toBot<thresh){window.scrollTo(sl,st+scrAmt);}
if(y<st&&st>0&&y-st<thresh){window.scrollTo(sl,st-scrAmt);}
if(right>clientW&&toRight<thresh){window.scrollTo(sl+scrAmt,st);}
if(x<sl&&sl>0&&x-sl<thresh){window.scrollTo(sl-scrAmt,st);}}},getTargetCoord:function(iPageX,iPageY){var x=iPageX-this.deltaX;var y=iPageY-this.deltaY;if(this.constrainX){if(x<this.minX){x=this.minX;}
if(x>this.maxX){x=this.maxX;}}
if(this.constrainY){if(y<this.minY){y=this.minY;}
if(y>this.maxY){y=this.maxY;}}
x=this.getTick(x,this.xTicks);y=this.getTick(y,this.yTicks);return{x:x,y:y};},applyConfig:function(){Ext.dd.DD.superclass.applyConfig.call(this);this.scroll=(this.config.scroll!==false);},b4MouseDown:function(e){this.autoOffset(e.getPageX(),e.getPageY());},b4Drag:function(e){this.setDragElPos(e.getPageX(),e.getPageY());},toString:function(){return("DD "+this.id);}});Ext.dd.DDProxy=function(id,sGroup,config){if(id){this.init(id,sGroup,config);this.initFrame();}};Ext.dd.DDProxy.dragElId="ygddfdiv";Ext.extend(Ext.dd.DDProxy,Ext.dd.DD,{resizeFrame:true,centerFrame:false,createFrame:function(){var self=this;var body=document.body;if(!body||!body.firstChild){setTimeout(function(){self.createFrame();},50);return;}
var div=this.getDragEl();if(!div){div=document.createElement("div");div.id=this.dragElId;var s=div.style;s.position="absolute";s.visibility="hidden";s.cursor="move";s.border="2px solid #aaa";s.zIndex=999;body.insertBefore(div,body.firstChild);}},initFrame:function(){this.createFrame();},applyConfig:function(){Ext.dd.DDProxy.superclass.applyConfig.call(this);this.resizeFrame=(this.config.resizeFrame!==false);this.centerFrame=(this.config.centerFrame);this.setDragElId(this.config.dragElId||Ext.dd.DDProxy.dragElId);},showFrame:function(iPageX,iPageY){var el=this.getEl();var dragEl=this.getDragEl();var s=dragEl.style;this._resizeProxy();if(this.centerFrame){this.setDelta(Math.round(parseInt(s.width,10)/2),Math.round(parseInt(s.height,10)/2));}
this.setDragElPos(iPageX,iPageY);Ext.fly(dragEl).show();},_resizeProxy:function(){if(this.resizeFrame){var el=this.getEl();Ext.fly(this.getDragEl()).setSize(el.offsetWidth,el.offsetHeight);}},b4MouseDown:function(e){var x=e.getPageX();var y=e.getPageY();this.autoOffset(x,y);this.setDragElPos(x,y);},b4StartDrag:function(x,y){this.showFrame(x,y);},b4EndDrag:function(e){Ext.fly(this.getDragEl()).hide();},endDrag:function(e){var lel=this.getEl();var del=this.getDragEl();del.style.visibility="";this.beforeMove();lel.style.visibility="hidden";Ext.dd.DDM.moveToEl(lel,del);del.style.visibility="hidden";lel.style.visibility="";this.afterDrag();},beforeMove:function(){},afterDrag:function(){},toString:function(){return("DDProxy "+this.id);}});Ext.dd.DDTarget=function(id,sGroup,config){if(id){this.initTarget(id,sGroup,config);}};Ext.extend(Ext.dd.DDTarget,Ext.dd.DragDrop,{toString:function(){return("DDTarget "+this.id);}});

Ext.dd.ScrollManager=function(){var ddm=Ext.dd.DragDropMgr;var els={};var dragEl=null;var proc={};var onStop=function(e){dragEl=null;clearProc();};var triggerRefresh=function(){if(ddm.dragCurrent){ddm.refreshCache(ddm.dragCurrent.groups);}};var doScroll=function(){if(ddm.dragCurrent){var dds=Ext.dd.ScrollManager;var inc=proc.el.ddScrollConfig?proc.el.ddScrollConfig.increment:dds.increment;if(!dds.animate){if(proc.el.scroll(proc.dir,inc)){triggerRefresh();}}else{proc.el.scroll(proc.dir,inc,true,dds.animDuration,triggerRefresh);}}};var clearProc=function(){if(proc.id){clearInterval(proc.id);}
proc.id=0;proc.el=null;proc.dir="";};var startProc=function(el,dir){clearProc();proc.el=el;proc.dir=dir;var freq=(el.ddScrollConfig&&el.ddScrollConfig.frequency)?el.ddScrollConfig.frequency:Ext.dd.ScrollManager.frequency;proc.id=setInterval(doScroll,freq);};var onFire=function(e,isDrop){if(isDrop||!ddm.dragCurrent){return;}
var dds=Ext.dd.ScrollManager;if(!dragEl||dragEl!=ddm.dragCurrent){dragEl=ddm.dragCurrent;dds.refreshCache();}
var xy=Ext.lib.Event.getXY(e);var pt=new Ext.lib.Point(xy[0],xy[1]);for(var id in els){var el=els[id],r=el._region;var c=el.ddScrollConfig?el.ddScrollConfig:dds;if(r&&r.contains(pt)&&el.isScrollable()){if(r.bottom-pt.y<=c.vthresh){if(proc.el!=el){startProc(el,"down");}
return;}else if(r.right-pt.x<=c.hthresh){if(proc.el!=el){startProc(el,"left");}
return;}else if(pt.y-r.top<=c.vthresh){if(proc.el!=el){startProc(el,"up");}
return;}else if(pt.x-r.left<=c.hthresh){if(proc.el!=el){startProc(el,"right");}
return;}}}
clearProc();};ddm.fireEvents=ddm.fireEvents.createSequence(onFire,ddm);ddm.stopDrag=ddm.stopDrag.createSequence(onStop,ddm);return{register:function(el){if(Ext.isArray(el)){for(var i=0,len=el.length;i<len;i++){this.register(el[i]);}}else{el=Ext.get(el);els[el.id]=el;}},unregister:function(el){if(Ext.isArray(el)){for(var i=0,len=el.length;i<len;i++){this.unregister(el[i]);}}else{el=Ext.get(el);delete els[el.id];}},vthresh:25,hthresh:25,increment:100,frequency:500,animate:true,animDuration:.4,refreshCache:function(){for(var id in els){if(typeof els[id]=='object'){els[id]._region=els[id].getRegion();}}}};}();

Ext.dd.Registry=function(){var elements={};var handles={};var autoIdSeed=0;var getId=function(el,autogen){if(typeof el=="string"){return el;}
var id=el.id;if(!id&&autogen!==false){id="extdd-"+(++autoIdSeed);el.id=id;}
return id;};return{register:function(el,data){data=data||{};if(typeof el=="string"){el=document.getElementById(el);}
data.ddel=el;elements[getId(el)]=data;if(data.isHandle!==false){handles[data.ddel.id]=data;}
if(data.handles){var hs=data.handles;for(var i=0,len=hs.length;i<len;i++){handles[getId(hs[i])]=data;}}},unregister:function(el){var id=getId(el,false);var data=elements[id];if(data){delete elements[id];if(data.handles){var hs=data.handles;for(var i=0,len=hs.length;i<len;i++){delete handles[getId(hs[i],false)];}}}},getHandle:function(id){if(typeof id!="string"){id=id.id;}
return handles[id];},getHandleFromEvent:function(e){var t=Ext.lib.Event.getTarget(e);return t?handles[t.id]:null;},getTarget:function(id){if(typeof id!="string"){id=id.id;}
return elements[id];},getTargetFromEvent:function(e){var t=Ext.lib.Event.getTarget(e);return t?elements[t.id]||handles[t.id]:null;}};}();

Ext.dd.StatusProxy=function(config){Ext.apply(this,config);this.id=this.id||Ext.id();this.el=new Ext.Layer({dh:{id:this.id,tag:"div",cls:"x-dd-drag-proxy "+this.dropNotAllowed,children:[{tag:"div",cls:"x-dd-drop-icon"},{tag:"div",cls:"x-dd-drag-ghost"}]},shadow:!config||config.shadow!==false});this.ghost=Ext.get(this.el.dom.childNodes[1]);this.dropStatus=this.dropNotAllowed;};Ext.dd.StatusProxy.prototype={dropAllowed:"x-dd-drop-ok",dropNotAllowed:"x-dd-drop-nodrop",setStatus:function(cssClass){cssClass=cssClass||this.dropNotAllowed;if(this.dropStatus!=cssClass){this.el.replaceClass(this.dropStatus,cssClass);this.dropStatus=cssClass;}},reset:function(clearGhost){this.el.dom.className="x-dd-drag-proxy "+this.dropNotAllowed;this.dropStatus=this.dropNotAllowed;if(clearGhost){this.ghost.update("");}},update:function(html){if(typeof html=="string"){this.ghost.update(html);}else{this.ghost.update("");html.style.margin="0";this.ghost.dom.appendChild(html);}
var el=this.ghost.dom.firstChild;if(el){Ext.fly(el).setStyle('float','none');}},getEl:function(){return this.el;},getGhost:function(){return this.ghost;},hide:function(clear){this.el.hide();if(clear){this.reset(true);}},stop:function(){if(this.anim&&this.anim.isAnimated&&this.anim.isAnimated()){this.anim.stop();}},show:function(){this.el.show();},sync:function(){this.el.sync();},repair:function(xy,callback,scope){this.callback=callback;this.scope=scope;if(xy&&this.animRepair!==false){this.el.addClass("x-dd-drag-repair");this.el.hideUnders(true);this.anim=this.el.shift({duration:this.repairDuration||.5,easing:'easeOut',xy:xy,stopFx:true,callback:this.afterRepair,scope:this});}else{this.afterRepair();}},afterRepair:function(){this.hide(true);if(typeof this.callback=="function"){this.callback.call(this.scope||this);}
this.callback=null;this.scope=null;}};

Ext.dd.DragSource=function(el,config){this.el=Ext.get(el);if(!this.dragData){this.dragData={};}
Ext.apply(this,config);if(!this.proxy){this.proxy=new Ext.dd.StatusProxy();}
Ext.dd.DragSource.superclass.constructor.call(this,this.el.dom,this.ddGroup||this.group,{dragElId:this.proxy.id,resizeFrame:false,isTarget:false,scroll:this.scroll===true});this.dragging=false;};Ext.extend(Ext.dd.DragSource,Ext.dd.DDProxy,{dropAllowed:"x-dd-drop-ok",dropNotAllowed:"x-dd-drop-nodrop",getDragData:function(e){return this.dragData;},onDragEnter:function(e,id){var target=Ext.dd.DragDropMgr.getDDById(id);this.cachedTarget=target;if(this.beforeDragEnter(target,e,id)!==false){if(target.isNotifyTarget){var status=target.notifyEnter(this,e,this.dragData);this.proxy.setStatus(status);}else{this.proxy.setStatus(this.dropAllowed);}
if(this.afterDragEnter){this.afterDragEnter(target,e,id);}}},beforeDragEnter:function(target,e,id){return true;},alignElWithMouse:function(){Ext.dd.DragSource.superclass.alignElWithMouse.apply(this,arguments);this.proxy.sync();},onDragOver:function(e,id){var target=this.cachedTarget||Ext.dd.DragDropMgr.getDDById(id);if(this.beforeDragOver(target,e,id)!==false){if(target.isNotifyTarget){var status=target.notifyOver(this,e,this.dragData);this.proxy.setStatus(status);}
if(this.afterDragOver){this.afterDragOver(target,e,id);}}},beforeDragOver:function(target,e,id){return true;},onDragOut:function(e,id){var target=this.cachedTarget||Ext.dd.DragDropMgr.getDDById(id);if(this.beforeDragOut(target,e,id)!==false){if(target.isNotifyTarget){target.notifyOut(this,e,this.dragData);}
this.proxy.reset();if(this.afterDragOut){this.afterDragOut(target,e,id);}}
this.cachedTarget=null;},beforeDragOut:function(target,e,id){return true;},onDragDrop:function(e,id){var target=this.cachedTarget||Ext.dd.DragDropMgr.getDDById(id);if(this.beforeDragDrop(target,e,id)!==false){if(target.isNotifyTarget){if(target.notifyDrop(this,e,this.dragData)){this.onValidDrop(target,e,id);}else{this.onInvalidDrop(target,e,id);}}else{this.onValidDrop(target,e,id);}
if(this.afterDragDrop){this.afterDragDrop(target,e,id);}}
delete this.cachedTarget;},beforeDragDrop:function(target,e,id){return true;},onValidDrop:function(target,e,id){this.hideProxy();if(this.afterValidDrop){this.afterValidDrop(target,e,id);}},getRepairXY:function(e,data){return this.el.getXY();},onInvalidDrop:function(target,e,id){this.beforeInvalidDrop(target,e,id);if(this.cachedTarget){if(this.cachedTarget.isNotifyTarget){this.cachedTarget.notifyOut(this,e,this.dragData);}
this.cacheTarget=null;}
this.proxy.repair(this.getRepairXY(e,this.dragData),this.afterRepair,this);if(this.afterInvalidDrop){this.afterInvalidDrop(e,id);}},afterRepair:function(){if(Ext.enableFx){this.el.highlight(this.hlColor||"c3daf9");}
this.dragging=false;},beforeInvalidDrop:function(target,e,id){return true;},handleMouseDown:function(e){if(this.dragging){return;}
var data=this.getDragData(e);if(data&&this.onBeforeDrag(data,e)!==false){this.dragData=data;this.proxy.stop();Ext.dd.DragSource.superclass.handleMouseDown.apply(this,arguments);}},onBeforeDrag:function(data,e){return true;},onStartDrag:Ext.emptyFn,startDrag:function(x,y){this.proxy.reset();this.dragging=true;this.proxy.update("");this.onInitDrag(x,y);this.proxy.show();},onInitDrag:function(x,y){var clone=this.el.dom.cloneNode(true);clone.id=Ext.id();this.proxy.update(clone);this.onStartDrag(x,y);return true;},getProxy:function(){return this.proxy;},hideProxy:function(){this.proxy.hide();this.proxy.reset(true);this.dragging=false;},triggerCacheRefresh:function(){Ext.dd.DDM.refreshCache(this.groups);},b4EndDrag:function(e){},endDrag:function(e){this.onEndDrag(this.dragData,e);},onEndDrag:function(data,e){},autoOffset:function(x,y){this.setDelta(-12,-20);}});

Ext.dd.DropTarget=function(el,config){this.el=Ext.get(el);Ext.apply(this,config);if(this.containerScroll){Ext.dd.ScrollManager.register(this.el);}
Ext.dd.DropTarget.superclass.constructor.call(this,this.el.dom,this.ddGroup||this.group,{isTarget:true});};Ext.extend(Ext.dd.DropTarget,Ext.dd.DDTarget,{dropAllowed:"x-dd-drop-ok",dropNotAllowed:"x-dd-drop-nodrop",isTarget:true,isNotifyTarget:true,notifyEnter:function(dd,e,data){if(this.overClass){this.el.addClass(this.overClass);}
return this.dropAllowed;},notifyOver:function(dd,e,data){return this.dropAllowed;},notifyOut:function(dd,e,data){if(this.overClass){this.el.removeClass(this.overClass);}},notifyDrop:function(dd,e,data){return false;}});

Ext.dd.DragZone=function(el,config){Ext.dd.DragZone.superclass.constructor.call(this,el,config);if(this.containerScroll){Ext.dd.ScrollManager.register(this.el);}};Ext.extend(Ext.dd.DragZone,Ext.dd.DragSource,{getDragData:function(e){return Ext.dd.Registry.getHandleFromEvent(e);},onInitDrag:function(x,y){this.proxy.update(this.dragData.ddel.cloneNode(true));this.onStartDrag(x,y);return true;},afterRepair:function(){if(Ext.enableFx){Ext.Element.fly(this.dragData.ddel).highlight(this.hlColor||"c3daf9");}
this.dragging=false;},getRepairXY:function(e){return Ext.Element.fly(this.dragData.ddel).getXY();}});

Ext.dd.DropZone=function(el,config){Ext.dd.DropZone.superclass.constructor.call(this,el,config);};Ext.extend(Ext.dd.DropZone,Ext.dd.DropTarget,{getTargetFromEvent:function(e){return Ext.dd.Registry.getTargetFromEvent(e);},onNodeEnter:function(n,dd,e,data){},onNodeOver:function(n,dd,e,data){return this.dropAllowed;},onNodeOut:function(n,dd,e,data){},onNodeDrop:function(n,dd,e,data){return false;},onContainerOver:function(dd,e,data){return this.dropNotAllowed;},onContainerDrop:function(dd,e,data){return false;},notifyEnter:function(dd,e,data){return this.dropNotAllowed;},notifyOver:function(dd,e,data){var n=this.getTargetFromEvent(e);if(!n){if(this.lastOverNode){this.onNodeOut(this.lastOverNode,dd,e,data);this.lastOverNode=null;}
return this.onContainerOver(dd,e,data);}
if(this.lastOverNode!=n){if(this.lastOverNode){this.onNodeOut(this.lastOverNode,dd,e,data);}
this.onNodeEnter(n,dd,e,data);this.lastOverNode=n;}
return this.onNodeOver(n,dd,e,data);},notifyOut:function(dd,e,data){if(this.lastOverNode){this.onNodeOut(this.lastOverNode,dd,e,data);this.lastOverNode=null;}},notifyDrop:function(dd,e,data){if(this.lastOverNode){this.onNodeOut(this.lastOverNode,dd,e,data);this.lastOverNode=null;}
var n=this.getTargetFromEvent(e);return n?this.onNodeDrop(n,dd,e,data):this.onContainerDrop(dd,e,data);},triggerCacheRefresh:function(){Ext.dd.DDM.refreshCache(this.groups);}});