# Git

1. 版本控制：
   + 集中式SVN：因为每次存的都是差异，需要的硬盘空间相对小一点，存放在单一服务器上，便于项目管理，若服务器宕机，项目资料全部丢失。
   + 分布式Git：每次存完整的项目快照，算法压缩存储空间，硬盘空间相对大一点。断网的情况下也可以进行开发，任何单点设备都保存完整项目记录。

2. 下载：https://git-scm.com/

3. 配置

   + 用户信息：

     ```
     #1.查看配置
     git config --list
     
     #2.配置个人用户名和电子邮箱
     git config --global[system] user.name "example"
     git config --global[system] user.email example@example.com
     
     --system 当前系统配置有效
     --global 当前用户配置有效
     --不填 当前项目配置有效
     
     #3.删除配置信息  
     git config unset 
     ```

4. 区域
   + 工作区
   + 暂存区
   + 版本库

5. 对象
   + Git对象
   + 树对象
   + 提交对象

6. 高层命令

   ```
   #当前目录文件到版本库，版本库到暂存区
   git add ./
   
   #将暂存区提交至版本库
   git commit -m "注释内容"
   
   #查看文件的状态
   git status
   
   #查看已修改未暂存
   git diff
   
   #查看已暂存为提交
   git diff --staged
   
   #跳过暂存区，直接将已修改文件提交至版本库
   git commit -a
   
   #删除工作目录中对应的文件，再将修改添加至暂存区
   git rm 文件名
   
   #将工作目录中的文件进行重命名，再将修改添加至暂存区
   git mv 原文件名 新文件名
   
   #查看提交的历史记录
   git log --oneline
   
   #配别名
   git config --global alias.name git 命令
   ```

7. 文件的状态
   + 未跟踪
   + 已跟踪
     + 已修改   
     + 已修改
     + 已暂存

8. 分支命令

   ```
   #显示分支列表
   git branch
   
   #查看分支图
   git log --graph --decorate --oneline --all
   
   #查看HEAD的变化
   git reflog
   
   #创建分支
   git branch 分支名
   
   #切换分支
   git checkout 分支名
   
   #删除分支 
   git branch -d 分支名 //强制删除未合并的分支 -D
   
   #查看每一个分支的最后一次提交
   git branch -v
   
   #新建一个分支并且使分支指向对应的提交对象
   git branch name commitHash
   
   #合并分支，先切换至主分支，再合并
   git merge 分支名
   
   #查看哪些分支已经合并到当前分支
   git branch --merged
   
   #查看没有合并到当前分支的分支列表
   git branch --no-merged
   
   #版本穿梭
   git branch 分支名 commitHash
   ```

9. 存储

   ```
   #查看栈存储清单
   git stash list
   
   #存储一个已修改未提交的任务
   git stash
   
   #恢复栈顶元素
   git stash apply
   
   #删除栈顶元素
   git stash drop 存储名字
   
   #弹出栈应用并删除
   git stash pop0
   ```

10. 后悔药

    ```
    #撤销工作区的修改
    git restore -- filename
    
    #撤销暂存区的修改
    git reset filename
    
    #撤销已提交内容，先修改提交内容，再git add 文件名
    git commit --amend
    
    #reset三部曲
    #1.切换HEAD，分支
    git reset --soft HEAD~
    
    #2.切换HEAD，分支，暂存区
    git reset --mixed HEAD~
    
    #3.切换HEAD，分支，暂存区，工作区
    git reset --hard HEAD~
    ```

11. 数据恢复

    ```
    #硬重置后恢复到原提交对象
    git branch recover-branch 提交对象HASH值
    ```

12. tag

    ```
    #1.显示所有标签
    git tag
    
    #2.创建标签
    git tag 标签名 提交对象HASH值
    
    #3.查看指定标签
    git show 标签名
    
    #4.删除标签
    git tag -d 标签名
    
    #5.检出标签
    git checkout -b 标签名
    ```


## GitHub

1. github访问慢问题

   ```
   #1.打开www.ipaddress.com
   
   #2.输入
   github.com 
   github.global.ssl.fastly.net 
   codeload.github.com
   s3.amazonaws.com
   github-cloud.s3.amazonaws.com
   复制ip
   
   #3.管理员打开powershell 
   cd C:\Windows\System32\drivers\etc
   notepad hosts
   
   #4.刷新dns
   ipconfig /flushdns
   ```

2. 创建本地仓库

   ```
   #1.新建仓库文件夹
   #2.新建项目文件
   #3.git init
   #配置用户信息
   #4.git add./
   #5.git commit -m ""
   ```

3. 创建远程仓库

   ```
   #访问github.com创建一个新的远程仓库
   #获取push访问地址
   ```

4. push

   ```
   #1.配置别名
   git remote add [rename] pushurl
   
   #2.push
   git push -u rename branch
   ```

5. clone

   ```
   git clone url
   ```

6. 团队合作

   ```
   #1.项目经理初始化远程仓库
   一定要初始化一个空的仓库，在github上操作
   
   #2.项目经理创建本地仓库
   git remote 别名 仓库地址(https)
   git init 
   将源码复制进来
   修改用户名 修改邮箱
   git add
   git commit
   
   #3.项目经理推出本地仓库到远程仓库
   清理windows凭据
   git push 别名 分支 （输入用户名 密码，附带生成远程跟踪分支）
   
   #4.项目经理邀请成员，成员接受邀请
   在github上操作
   
   #5.成员克隆远程仓库
   git clone 仓库地址（在本地生成.git文件 默认为远程仓库配置了别名 orgin）
   
   #6.成员做出贡献
   修改源码文件
   git add
   git commit
   git push 别名 分支（输入用户名 密码，附带生成远程跟踪分支）
   
   #7.项目经理更新修改
   git fetch 别名（将修改同步到远程跟踪分支上）
   git merge 远程跟踪分支000
   ```

   