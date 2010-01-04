
(ns com.ociweb.compressor-test
  (:use clojure.test
	com.ociweb.compressor))

(deftest test-is-public-proc?
  (is (= true (is-public-procedure? "	public void setTeamDAS(TeamDAS teamDAS) {")))
  (is (= true (is-public-procedure? "public ModelAndView createProjectType(@RequestParam(\"metadata\") String metadata) throws Exception{")))
  (is (= false (is-public-procedure? "public EmployeeLdapDaoImpl(EmployeeDAO dao, String rdn) {")))
  (is (= true (is-public-procedure? "  public Set<Pair<String, IEmployee>> resolve(Set<Pair<String, IEmployee>> in) {"))))

(deftest test-compress-line
  (is (= "{" (compress-line "for(Release rel : this.releaseDAS.findReleases(query)){")))
  (is (= "" (compress-line "@RequestMapping(\"/releases/fetchRelease.do\")"))))

(deftest test-compressed-line-terminated?
  (is (= true (compressed-proc-terminated? "{;;;{}}")))
  (is (= false (compressed-proc-terminated? "")))
  (is (= false (compressed-proc-terminated? "{")))
  (is (thrown? IllegalStateException (compressed-proc-terminated? "}}")))
  (is (thrown? IllegalStateException (compressed-proc-terminated? "{{}}}"))))

(deftest test-compress-lines
  (let [lines (list "public ReleaseDAS getReleaseDAS() {"
		    "return releaseDAS;"
		    "}"
		    ""
		    "public void setReleaseDAS(ReleaseDAS releaseDAS) {"
		    "this.releaseDAS = releaseDAS;"
		    "}"
		    "}")]
    (is (= "" (compress-lines '() "")))
    (is (= "{;}" (compress-lines lines "")))))

(deftest test-lines2summarys 
  (let [lines (list "public class ReleasesController {"
		    "private ReleaseDAS releaseDAS;" 
		    ""
		    "public ReleaseDAS getReleaseDAS() {"
		    "return releaseDAS;"
		    "}"
		    ""
		    "public void setReleaseDAS(ReleaseDAS releaseDAS) {"
		    "this.releaseDAS = releaseDAS;"
		    "}"
		    "}")
	sum (list "setReleaseDAS" "{;}" "getReleaseDAS" "{;}" )]
    (is (= sum (lines2summarys lines)))))