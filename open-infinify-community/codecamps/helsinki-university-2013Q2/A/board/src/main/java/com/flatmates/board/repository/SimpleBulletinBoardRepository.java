package com.flatmates.board.repository;

import com.flatmates.board.domain.entity.BulletinBoard;
import com.flatmates.board.domain.entity.Sticker;
import com.flatmates.board.domain.repository.BulletinBoardRepository;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;
/**
 *
 * @author kavan soleimanbeigi
 */
@Repository
public class SimpleBulletinBoardRepository implements BulletinBoardRepository {

    Collection<BulletinBoard> bbmanager = new LinkedList<BulletinBoard>();
//	Collection<BulletinBoard> bbmanager = RepoTool.BOARD_REPO;
    Collection<Sticker> stickerManager = new LinkedList<Sticker>();
//	Collection<Sticker> stickerManager = RepoTool.STICKER_REPO;
/**
 * 
 * @param board
 * @return 
 */
    @Override
    public String saveBulletinBoard(BulletinBoard board) {
        String board_id = UUID.randomUUID().toString();
        board_id = board_id.replace('-', 'x');
        board.setId(board_id);
        if (board.getBuilding_id() != null && !board.getBuilding_id().equalsIgnoreCase("")
                && !RepoTool.otherBoardHasSameBuildingId(board.getBuilding_id(), board_id, bbmanager)) {
            bbmanager.add(board);
            return board.getId();
        }
        return null;

    }
/**
 * 
 * @return 
 */
    @Override
    public Collection<BulletinBoard> listAllBoards() {
        return bbmanager;
    }
/**
 * 
 * @param board_id
 * @return 
 */
    @Override
    public BulletinBoard findBoardById(String board_id) {
        for (BulletinBoard bb : bbmanager) {
            if (bb.getId().equalsIgnoreCase(board_id)) {
                return bb;
            }
        }
        return null;
    }
/**
 * 
 * @param board_id
 * @param sticker 
 */
    @Override
    public void addStickerToBoard(String board_id, Sticker sticker) {
        sticker.setBulletin_id(board_id);
        String sticker_id = UUID.randomUUID().toString().replace('-', 'x');
        sticker.setId(sticker_id);
        if (RepoTool.isStickerAuthenticationDataOk(sticker)
                && !RepoTool.stickerIdExistInRepo(sticker.getId(), stickerManager)) {
            for (BulletinBoard bb : bbmanager) {
                if (bb.getId().equalsIgnoreCase(board_id)) {
                    bb.addSticker(sticker);
                }
            }
            stickerManager.add(sticker);
        }

    }
/**
 * 
 * @param bulletin_id
 * @param sticker_id
 * @return 
 */
    @Override
    public Sticker findStickerById(String bulletin_id, String sticker_id) {
        for (Sticker sticker : stickerManager) {
            if (sticker.getId().equalsIgnoreCase(sticker_id)
                    && sticker.getBulletin_id().equalsIgnoreCase(bulletin_id)) {
                return sticker;

            }
        }
        return null;
    }
/**
 * 
 * @param board_id
 * @return 
 */
    @Override
    public Collection<Sticker> findAllStickers(String board_id) {
        Collection<Sticker> wantedList = new LinkedList<Sticker>();
        for (Sticker sticker : stickerManager) {
            if (sticker.getBulletin_id().equalsIgnoreCase(board_id)) {
                wantedList.add(sticker);
            }
        }
        return wantedList;
    }
/**
 * 
 * @param sticker 
 */
    @Override
    public void removeStickerFromBoard(Sticker sticker) {
        String id = sticker.getBulletin_id();
        for (BulletinBoard bb : bbmanager) {
            if (bb.getId().equalsIgnoreCase(id)) {
                bb.getStickers().remove(sticker);
            }
        }

        stickerManager.remove(sticker);
    }
/**
 * 
 * @param board_id 
 */
    @Override
    public void removeBoardById(String board_id) {
        BulletinBoard[] boards = bbmanager.toArray(new BulletinBoard[bbmanager.size()]);
        BulletinBoard toBeremoved = null;
        for (BulletinBoard b : boards) {
            if (b.getId().equalsIgnoreCase(board_id)) {
                toBeremoved = b;
            }
        }
        if (toBeremoved != null) {
            bbmanager.remove(toBeremoved);
        }
    }
/**
 * 
 * @param board_id 
 */
    @Override
    public void removeAllStickersFromBoard(String board_id) {
        Sticker[] sts = stickerManager.toArray(new Sticker[stickerManager.size()]);
        List<Sticker> toBeRemoved = new LinkedList<Sticker>();
        for (Sticker s : sts) {
            if (s.getBulletin_id().equalsIgnoreCase(board_id)) {
                toBeRemoved.add(s);
            }
        }
        for (Sticker s : toBeRemoved) {
            stickerManager.remove(s);
        }


    }
}
