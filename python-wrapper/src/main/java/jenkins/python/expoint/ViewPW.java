package jenkins.python.expoint;


import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.XppDriver;
import hudson.DescriptorExtensionList;
import hudson.ExtensionPoint;
import hudson.Functions;
import hudson.Indenter;
import hudson.Util;
import hudson.model.Descriptor.FormException;
import hudson.model.labels.LabelAtomPropertyDescriptor;
import hudson.scm.ChangeLogSet;
import hudson.scm.ChangeLogSet.Entry;
import hudson.search.CollectionSearchIndex;
import hudson.search.SearchIndexBuilder;
import hudson.security.ACL;
import hudson.security.AccessControlled;
import hudson.security.Permission;
import hudson.security.PermissionGroup;
import hudson.security.PermissionScope;
import hudson.tasks.UserAvatarResolver;
import hudson.util.AlternativeUiTextProvider;
import hudson.util.AlternativeUiTextProvider.Message;
import hudson.util.DescribableList;
import hudson.util.DescriptorList;
import hudson.util.FormApply;
import hudson.util.IOException2;
import hudson.util.RunList;
import hudson.util.XStream2;
import hudson.views.ListViewColumn;
import hudson.widgets.Widget;
import jenkins.model.Jenkins;
import jenkins.util.ProgressiveRendering;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.HttpResponses;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.WebMethod;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;
import org.kohsuke.stapler.interceptor.RequirePOST;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jenkins.model.Jenkins.*;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import hudson.model.*;
import hudson.model.View.*;
import hudson.search.SearchFactory;
import hudson.search.SearchableModelObject;
import hudson.search.Search;
import hudson.search.SearchIndex;
import hudson.model.AbstractModelObject.*;
import jenkins.python.DataConvertor;
import jenkins.python.PythonExecutor;

/**
 * This class was automatically generated by the PWM tool on 2014/03/21.
 * @see hudson.model.View
 */
public abstract class ViewPW extends View {
	private transient PythonExecutor pexec;

	private void initPython() {
		if (pexec == null) {
			pexec = new PythonExecutor(this);
			String[] jMethods = new String[5];
			jMethods[0] = "getItems";
			jMethods[1] = "contains";
			jMethods[2] = "onJobRenamed";
			jMethods[3] = "submit";
			jMethods[4] = "doCreateItem";
			String[] pFuncs = new String[5];
			pFuncs[0] = "get_items";
			pFuncs[1] = "contains";
			pFuncs[2] = "on_job_renamed";
			pFuncs[3] = "submit";
			pFuncs[4] = "do_create_item";
			Class[][] argTypes = new Class[5][];
			argTypes[0] = new Class[0];
			argTypes[1] = new Class[1];
			argTypes[1][0] = TopLevelItem.class;
			argTypes[2] = new Class[3];
			argTypes[2][0] = Item.class;
			argTypes[2][1] = String.class;
			argTypes[2][2] = String.class;
			argTypes[3] = new Class[1];
			argTypes[3][0] = StaplerRequest.class;
			argTypes[4] = new Class[2];
			argTypes[4][0] = StaplerRequest.class;
			argTypes[4][1] = StaplerResponse.class;
			pexec.checkAbstrMethods(jMethods, pFuncs, argTypes);
			String[] functions = new String[52];
			functions[0] = "get_item";
			functions[1] = "get_view_name";
			functions[2] = "rename";
			functions[3] = "get_owner";
			functions[4] = "get_owner_item_group";
			functions[5] = "get_owner_primary_view";
			functions[6] = "get_owner_view_actions";
			functions[7] = "get_description";
			functions[8] = "get_properties";
			functions[9] = "get_applicable_property_descriptors";
			functions[10] = "save";
			functions[11] = "get_all_properties";
			functions[12] = "get_descriptor";
			functions[13] = "get_display_name";
			functions[14] = "get_new_pronoun";
			functions[15] = "is_editable";
			functions[16] = "is_filter_executors";
			functions[17] = "is_filter_queue";
			functions[18] = "get_widgets";
			functions[19] = "get_columns";
			functions[20] = "get_indenter";
			functions[21] = "is_default";
			functions[22] = "get_computers";
			functions[23] = "get_queue_items";
			functions[24] = "get_approximate_queue_items_quickly";
			functions[25] = "get_url";
			functions[26] = "get_view_url";
			functions[27] = "get_search_url";
			functions[28] = "get_actions";
			functions[29] = "update_transient_actions";
			functions[30] = "get_dynamic";
			functions[31] = "get_absolute_url";
			functions[32] = "get_api";
			functions[33] = "get_post_construct_landing_page";
			functions[34] = "get_acl";
			functions[35] = "check_permission";
			functions[36] = "has_permission";
			functions[37] = "has_people";
			functions[38] = "get_people";
			functions[39] = "get_asynch_people";
			functions[40] = "make_search_index";
			functions[41] = "do_submit_description";
			functions[42] = "do_do_delete";
			functions[43] = "do_rss_all";
			functions[44] = "do_rss_failed";
			functions[45] = "get_builds";
			functions[46] = "get_timeline";
			functions[47] = "do_rss_latest";
			functions[48] = "do_config_dot_xml";
			functions[49] = "update_by_xml";
			functions[50] = "get_search";
			functions[51] = "get_search_name";
			int[] argsCount = new int[52];
			argsCount[0] = 1;
			argsCount[1] = 0;
			argsCount[2] = 1;
			argsCount[3] = 0;
			argsCount[4] = 0;
			argsCount[5] = 0;
			argsCount[6] = 0;
			argsCount[7] = 0;
			argsCount[8] = 0;
			argsCount[9] = 0;
			argsCount[10] = 0;
			argsCount[11] = 0;
			argsCount[12] = 0;
			argsCount[13] = 0;
			argsCount[14] = 0;
			argsCount[15] = 0;
			argsCount[16] = 0;
			argsCount[17] = 0;
			argsCount[18] = 0;
			argsCount[19] = 0;
			argsCount[20] = 0;
			argsCount[21] = 0;
			argsCount[22] = 0;
			argsCount[23] = 0;
			argsCount[24] = 0;
			argsCount[25] = 0;
			argsCount[26] = 0;
			argsCount[27] = 0;
			argsCount[28] = 0;
			argsCount[29] = 0;
			argsCount[30] = 1;
			argsCount[31] = 0;
			argsCount[32] = 0;
			argsCount[33] = 0;
			argsCount[34] = 0;
			argsCount[35] = 1;
			argsCount[36] = 1;
			argsCount[37] = 0;
			argsCount[38] = 0;
			argsCount[39] = 0;
			argsCount[40] = 0;
			argsCount[41] = 2;
			argsCount[42] = 2;
			argsCount[43] = 2;
			argsCount[44] = 2;
			argsCount[45] = 0;
			argsCount[46] = 0;
			argsCount[47] = 2;
			argsCount[48] = 1;
			argsCount[49] = 1;
			argsCount[50] = 0;
			argsCount[51] = 0;
			pexec.registerFunctions(functions, argsCount);
		}
	}

	public ViewPW(String name) {
		super(name);
	}

	public ViewPW(String name, ViewGroup owner) {
		super(name, owner);
	}

	@Override
	@Exported(name = "jobs")
	public Collection<TopLevelItem> getItems() {
		initPython();
		return (Collection) pexec.execPython("get_items");
	}

	@Override
	public boolean contains(TopLevelItem item) {
		initPython();
		return pexec.execPythonBool("contains", item);
	}

	@Override
	public void onJobRenamed(Item item, String oldName, String newName) {
		initPython();
		pexec.execPythonVoid("on_job_renamed", item, oldName, newName);
	}

	@Override
	public void submit(StaplerRequest req) throws IOException, ServletException, FormException {
		initPython();
		pexec.execPythonVoid("submit", req);
	}

	@Override
	public Item doCreateItem(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
		initPython();
		return (Item) pexec.execPython("do_create_item", req, rsp);
	}

	@Override
	public TopLevelItem getItem(String name) {
		initPython();
		if (pexec.isImplemented(0)) {
			return (TopLevelItem) pexec.execPython("get_item", name);
		} else {
			return super.getItem(name);
		}
	}

	@Override
	@Exported(visibility = 2, name = "name")
	public String getViewName() {
		initPython();
		if (pexec.isImplemented(1)) {
			return (String) pexec.execPython("get_view_name");
		} else {
			return super.getViewName();
		}
	}

	@Override
	public void rename(String newName) throws Failure, FormException {
		initPython();
		if (pexec.isImplemented(2)) {
			pexec.execPythonVoid("rename", newName);
		} else {
			super.rename(newName);
		}
	}

	@Override
	public ViewGroup getOwner() {
		initPython();
		if (pexec.isImplemented(3)) {
			return (ViewGroup) pexec.execPython("get_owner");
		} else {
			return super.getOwner();
		}
	}

	@Override
	public ItemGroup<? extends TopLevelItem> getOwnerItemGroup() {
		initPython();
		if (pexec.isImplemented(4)) {
			return (ItemGroup) pexec.execPython("get_owner_item_group");
		} else {
			return super.getOwnerItemGroup();
		}
	}

	@Override
	public View getOwnerPrimaryView() {
		initPython();
		if (pexec.isImplemented(5)) {
			return (View) pexec.execPython("get_owner_primary_view");
		} else {
			return super.getOwnerPrimaryView();
		}
	}

	@Override
	public List<Action> getOwnerViewActions() {
		initPython();
		if (pexec.isImplemented(6)) {
			return (List) pexec.execPython("get_owner_view_actions");
		} else {
			return super.getOwnerViewActions();
		}
	}

	@Override
	@Exported
	public String getDescription() {
		initPython();
		if (pexec.isImplemented(7)) {
			return (String) pexec.execPython("get_description");
		} else {
			return super.getDescription();
		}
	}

	@Override
	public DescribableList<ViewProperty, ViewPropertyDescriptor> getProperties() {
		initPython();
		if (pexec.isImplemented(8)) {
			return (DescribableList) pexec.execPython("get_properties");
		} else {
			return super.getProperties();
		}
	}

	@Override
	public List<ViewPropertyDescriptor> getApplicablePropertyDescriptors() {
		initPython();
		if (pexec.isImplemented(9)) {
			return (List) pexec.execPython("get_applicable_property_descriptors");
		} else {
			return super.getApplicablePropertyDescriptors();
		}
	}

	@Override
	public void save() throws IOException {
		initPython();
		if (pexec.isImplemented(10)) {
			pexec.execPythonVoid("save");
		} else {
			super.save();
		}
	}

	@Override
	@Exported(name = "property", inline = true)
	public List<ViewProperty> getAllProperties() {
		initPython();
		if (pexec.isImplemented(11)) {
			return (List) pexec.execPython("get_all_properties");
		} else {
			return super.getAllProperties();
		}
	}

	@Override
	public ViewDescriptor getDescriptor() {
		initPython();
		if (pexec.isImplemented(12)) {
			return (ViewDescriptor) pexec.execPython("get_descriptor");
		} else {
			return super.getDescriptor();
		}
	}

	@Override
	public String getDisplayName() {
		initPython();
		if (pexec.isImplemented(13)) {
			return (String) pexec.execPython("get_display_name");
		} else {
			return super.getDisplayName();
		}
	}

	@Override
	public String getNewPronoun() {
		initPython();
		if (pexec.isImplemented(14)) {
			return (String) pexec.execPython("get_new_pronoun");
		} else {
			return super.getNewPronoun();
		}
	}

	@Override
	public boolean isEditable() {
		initPython();
		if (pexec.isImplemented(15)) {
			return pexec.execPythonBool("is_editable");
		} else {
			return super.isEditable();
		}
	}

	@Override
	public boolean isFilterExecutors() {
		initPython();
		if (pexec.isImplemented(16)) {
			return pexec.execPythonBool("is_filter_executors");
		} else {
			return super.isFilterExecutors();
		}
	}

	@Override
	public boolean isFilterQueue() {
		initPython();
		if (pexec.isImplemented(17)) {
			return pexec.execPythonBool("is_filter_queue");
		} else {
			return super.isFilterQueue();
		}
	}

	@Override
	public List<Widget> getWidgets() {
		initPython();
		if (pexec.isImplemented(18)) {
			return (List) pexec.execPython("get_widgets");
		} else {
			return super.getWidgets();
		}
	}

	@Override
	public Iterable<? extends ListViewColumn> getColumns() {
		initPython();
		if (pexec.isImplemented(19)) {
			return (Iterable) pexec.execPython("get_columns");
		} else {
			return super.getColumns();
		}
	}

	@Override
	public Indenter getIndenter() {
		initPython();
		if (pexec.isImplemented(20)) {
			return (Indenter) pexec.execPython("get_indenter");
		} else {
			return super.getIndenter();
		}
	}

	@Override
	public boolean isDefault() {
		initPython();
		if (pexec.isImplemented(21)) {
			return pexec.execPythonBool("is_default");
		} else {
			return super.isDefault();
		}
	}

	@Override
	public List<Computer> getComputers() {
		initPython();
		if (pexec.isImplemented(22)) {
			return (List) pexec.execPython("get_computers");
		} else {
			return super.getComputers();
		}
	}

	@Override
	public List<Queue.Item> getQueueItems() {
		initPython();
		if (pexec.isImplemented(23)) {
			return (List) pexec.execPython("get_queue_items");
		} else {
			return super.getQueueItems();
		}
	}

	@Override
	public List<Queue.Item> getApproximateQueueItemsQuickly() {
		initPython();
		if (pexec.isImplemented(24)) {
			return (List) pexec.execPython("get_approximate_queue_items_quickly");
		} else {
			return super.getApproximateQueueItemsQuickly();
		}
	}

	@Override
	public String getUrl() {
		initPython();
		if (pexec.isImplemented(25)) {
			return (String) pexec.execPython("get_url");
		} else {
			return super.getUrl();
		}
	}

	@Override
	public String getViewUrl() {
		initPython();
		if (pexec.isImplemented(26)) {
			return (String) pexec.execPython("get_view_url");
		} else {
			return super.getViewUrl();
		}
	}

	@Override
	public String getSearchUrl() {
		initPython();
		if (pexec.isImplemented(27)) {
			return (String) pexec.execPython("get_search_url");
		} else {
			return super.getSearchUrl();
		}
	}

	@Override
	public List<Action> getActions() {
		initPython();
		if (pexec.isImplemented(28)) {
			return (List) pexec.execPython("get_actions");
		} else {
			return super.getActions();
		}
	}

	@Override
	public synchronized void updateTransientActions() {
		initPython();
		if (pexec.isImplemented(29)) {
			pexec.execPythonVoid("update_transient_actions");
		} else {
			super.updateTransientActions();
		}
	}

	@Override
	public Object getDynamic(String token) {
		initPython();
		if (pexec.isImplemented(30)) {
			return (Object) pexec.execPython("get_dynamic", token);
		} else {
			return super.getDynamic(token);
		}
	}

	@Override
	@Exported(visibility = 2, name = "url")
	public String getAbsoluteUrl() {
		initPython();
		if (pexec.isImplemented(31)) {
			return (String) pexec.execPython("get_absolute_url");
		} else {
			return super.getAbsoluteUrl();
		}
	}

	@Override
	public Api getApi() {
		initPython();
		if (pexec.isImplemented(32)) {
			return (Api) pexec.execPython("get_api");
		} else {
			return super.getApi();
		}
	}

	@Override
	public String getPostConstructLandingPage() {
		initPython();
		if (pexec.isImplemented(33)) {
			return (String) pexec.execPython("get_post_construct_landing_page");
		} else {
			return super.getPostConstructLandingPage();
		}
	}

	@Override
	public ACL getACL() {
		initPython();
		if (pexec.isImplemented(34)) {
			return (ACL) pexec.execPython("get_acl");
		} else {
			return super.getACL();
		}
	}

	@Override
	public void checkPermission(Permission p) {
		initPython();
		if (pexec.isImplemented(35)) {
			pexec.execPythonVoid("check_permission", p);
		} else {
			super.checkPermission(p);
		}
	}

	@Override
	public boolean hasPermission(Permission p) {
		initPython();
		if (pexec.isImplemented(36)) {
			return pexec.execPythonBool("has_permission", p);
		} else {
			return super.hasPermission(p);
		}
	}

	@Override
	public boolean hasPeople() {
		initPython();
		if (pexec.isImplemented(37)) {
			return pexec.execPythonBool("has_people");
		} else {
			return super.hasPeople();
		}
	}

	@Override
	public People getPeople() {
		initPython();
		if (pexec.isImplemented(38)) {
			return (People) pexec.execPython("get_people");
		} else {
			return super.getPeople();
		}
	}

	@Override
	public AsynchPeople getAsynchPeople() {
		initPython();
		if (pexec.isImplemented(39)) {
			return (AsynchPeople) pexec.execPython("get_asynch_people");
		} else {
			return super.getAsynchPeople();
		}
	}

	@Override
	public SearchIndexBuilder makeSearchIndex() {
		initPython();
		if (pexec.isImplemented(40)) {
			return (SearchIndexBuilder) pexec.execPython("make_search_index");
		} else {
			return super.makeSearchIndex();
		}
	}

	@Override
	public synchronized void doSubmitDescription(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
		initPython();
		if (pexec.isImplemented(41)) {
			pexec.execPythonVoid("do_submit_description", req, rsp);
		} else {
			super.doSubmitDescription(req, rsp);
		}
	}

	@Override
	@RequirePOST
	public synchronized void doDoDelete(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
		initPython();
		if (pexec.isImplemented(42)) {
			pexec.execPythonVoid("do_do_delete", req, rsp);
		} else {
			super.doDoDelete(req, rsp);
		}
	}

	@Override
	public void doRssAll(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
		initPython();
		if (pexec.isImplemented(43)) {
			pexec.execPythonVoid("do_rss_all", req, rsp);
		} else {
			super.doRssAll(req, rsp);
		}
	}

	@Override
	public void doRssFailed(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
		initPython();
		if (pexec.isImplemented(44)) {
			pexec.execPythonVoid("do_rss_failed", req, rsp);
		} else {
			super.doRssFailed(req, rsp);
		}
	}

	@Override
	public RunList getBuilds() {
		initPython();
		if (pexec.isImplemented(45)) {
			return (RunList) pexec.execPython("get_builds");
		} else {
			return super.getBuilds();
		}
	}

	@Override
	public BuildTimelineWidget getTimeline() {
		initPython();
		if (pexec.isImplemented(46)) {
			return (BuildTimelineWidget) pexec.execPython("get_timeline");
		} else {
			return super.getTimeline();
		}
	}

	@Override
	public void doRssLatest(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
		initPython();
		if (pexec.isImplemented(47)) {
			pexec.execPythonVoid("do_rss_latest", req, rsp);
		} else {
			super.doRssLatest(req, rsp);
		}
	}

	@Override
	@WebMethod(name = "config.xml")
	public HttpResponse doConfigDotXml(StaplerRequest req) throws IOException {
		initPython();
		if (pexec.isImplemented(48)) {
			return (HttpResponse) pexec.execPython("do_config_dot_xml", req);
		} else {
			return super.doConfigDotXml(req);
		}
	}

	@Override
	public void updateByXml(Source source) throws IOException {
		initPython();
		if (pexec.isImplemented(49)) {
			pexec.execPythonVoid("update_by_xml", source);
		} else {
			super.updateByXml(source);
		}
	}

	@Override
	public Search getSearch() {
		initPython();
		if (pexec.isImplemented(50)) {
			return (Search) pexec.execPython("get_search");
		} else {
			return super.getSearch();
		}
	}

	@Override
	public String getSearchName() {
		initPython();
		if (pexec.isImplemented(51)) {
			return (String) pexec.execPython("get_search_name");
		} else {
			return super.getSearchName();
		}
	}

	public TopLevelItem superGetItem(String name) {
		return super.getItem(name);
	}

	public String superGetViewName() {
		return super.getViewName();
	}

	public void superRename(String newName) throws Failure, FormException {
		super.rename(newName);
	}

	public ViewGroup superGetOwner() {
		return super.getOwner();
	}

	public ItemGroup<? extends TopLevelItem> superGetOwnerItemGroup() {
		return super.getOwnerItemGroup();
	}

	public View superGetOwnerPrimaryView() {
		return super.getOwnerPrimaryView();
	}

	public List<Action> superGetOwnerViewActions() {
		return super.getOwnerViewActions();
	}

	public String superGetDescription() {
		return super.getDescription();
	}

	public DescribableList<ViewProperty, ViewPropertyDescriptor> superGetProperties() {
		return super.getProperties();
	}

	public List<ViewPropertyDescriptor> superGetApplicablePropertyDescriptors() {
		return super.getApplicablePropertyDescriptors();
	}

	public void superSave() throws IOException {
		super.save();
	}

	public List<ViewProperty> superGetAllProperties() {
		return super.getAllProperties();
	}

	public ViewDescriptor superGetDescriptor() {
		return super.getDescriptor();
	}

	public String superGetDisplayName() {
		return super.getDisplayName();
	}

	public String superGetNewPronoun() {
		return super.getNewPronoun();
	}

	public boolean superIsEditable() {
		return super.isEditable();
	}

	public boolean superIsFilterExecutors() {
		return super.isFilterExecutors();
	}

	public boolean superIsFilterQueue() {
		return super.isFilterQueue();
	}

	public List<Widget> superGetWidgets() {
		return super.getWidgets();
	}

	public Iterable<? extends ListViewColumn> superGetColumns() {
		return super.getColumns();
	}

	public Indenter superGetIndenter() {
		return super.getIndenter();
	}

	public boolean superIsDefault() {
		return super.isDefault();
	}

	public List<Computer> superGetComputers() {
		return super.getComputers();
	}

	public List<Queue.Item> superGetQueueItems() {
		return super.getQueueItems();
	}

	public List<Queue.Item> superGetApproximateQueueItemsQuickly() {
		return super.getApproximateQueueItemsQuickly();
	}

	public String superGetUrl() {
		return super.getUrl();
	}

	public String superGetViewUrl() {
		return super.getViewUrl();
	}

	public String superGetSearchUrl() {
		return super.getSearchUrl();
	}

	public List<Action> superGetActions() {
		return super.getActions();
	}

	public synchronized void superUpdateTransientActions() {
		super.updateTransientActions();
	}

	public Object superGetDynamic(String token) {
		return super.getDynamic(token);
	}

	public String superGetAbsoluteUrl() {
		return super.getAbsoluteUrl();
	}

	public Api superGetApi() {
		return super.getApi();
	}

	public String superGetPostConstructLandingPage() {
		return super.getPostConstructLandingPage();
	}

	public ACL superGetACL() {
		return super.getACL();
	}

	public void superCheckPermission(Permission p) {
		super.checkPermission(p);
	}

	public boolean superHasPermission(Permission p) {
		return super.hasPermission(p);
	}

	public boolean superHasPeople() {
		return super.hasPeople();
	}

	public People superGetPeople() {
		return super.getPeople();
	}

	public AsynchPeople superGetAsynchPeople() {
		return super.getAsynchPeople();
	}

	public SearchIndexBuilder superMakeSearchIndex() {
		return super.makeSearchIndex();
	}

	public synchronized void superDoSubmitDescription(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
		super.doSubmitDescription(req, rsp);
	}

	public synchronized void superDoDoDelete(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
		super.doDoDelete(req, rsp);
	}

	public void superDoRssAll(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
		super.doRssAll(req, rsp);
	}

	public void superDoRssFailed(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
		super.doRssFailed(req, rsp);
	}

	public RunList superGetBuilds() {
		return super.getBuilds();
	}

	public BuildTimelineWidget superGetTimeline() {
		return super.getTimeline();
	}

	public void superDoRssLatest(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
		super.doRssLatest(req, rsp);
	}

	public HttpResponse superDoConfigDotXml(StaplerRequest req) throws IOException {
		return super.doConfigDotXml(req);
	}

	public void superUpdateByXml(Source source) throws IOException {
		super.updateByXml(source);
	}

	public Search superGetSearch() {
		return super.getSearch();
	}

	public String superGetSearchName() {
		return super.getSearchName();
	}

	public Object execPython(String function, Object... params) {
		initPython();
		return pexec.execPython(function, params);
	}

	public byte execPythonByte(String function, Object... params) {
		initPython();
		return pexec.execPythonByte(function, params);
	}

	public short execPythonShort(String function, Object... params) {
		initPython();
		return pexec.execPythonShort(function, params);
	}

	public char execPythonChar(String function, Object... params) {
		initPython();
		return pexec.execPythonChar(function, params);
	}

	public int execPythonInt(String function, Object... params) {
		initPython();
		return pexec.execPythonInt(function, params);
	}

	public long execPythonLong(String function, Object... params) {
		initPython();
		return pexec.execPythonLong(function, params);
	}

	public float execPythonFloat(String function, Object... params) {
		initPython();
		return pexec.execPythonFloat(function, params);
	}

	public double execPythonDouble(String function, Object... params) {
		initPython();
		return pexec.execPythonDouble(function, params);
	}

	public boolean execPythonBool(String function, Object... params) {
		initPython();
		return pexec.execPythonBool(function, params);
	}

	public void execPythonVoid(String function, Object... params) {
		initPython();
		pexec.execPythonVoid(function, params);
	}
}