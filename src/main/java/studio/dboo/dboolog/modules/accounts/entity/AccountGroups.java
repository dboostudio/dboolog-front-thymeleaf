//package studio.dboo.dboolog.modules.accounts.entity;
//
//import studio.dboo.dboolog.modules.groups.entity.Groups;
//
//import javax.persistence.*;
//
//@Entity
//public class AccountGroups {
//
//    @Id @GeneratedValue
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="account_id")
//    private Account account;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="groups_id")
//    private Groups groups;
//}
