/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;


public class VoteDTO {
    
    private String candidateid;
    private String voterid;

    @Override
    public String toString() {
        return "VoteDTO{" + "candidateid=" + candidateid + ", voterid=" + voterid + '}';
    }

    public String getCandidateid() {
        return candidateid;
    }

    public void setCandidateid(String candidateid) {
        this.candidateid = candidateid;
    }

    public String getVoterid() {
        return voterid;
    }

    public void setVoterid(String voterid) {
        this.voterid = voterid;
    }

    public VoteDTO(String candidateid, String voterid) {
        this.candidateid = candidateid;
        this.voterid = voterid;
    }
}
