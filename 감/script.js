document.addEventListener('DOMContentLoaded', () => {
    // DOM 요소 가져오기
    const orderTableBody = document.getElementById('order-table-body');
    const newOrderBtn = document.getElementById('new-order-btn');
    const modal = document.getElementById('order-form-modal');
    const closeModalBtn = document.querySelector('.close-btn');
    const orderForm = document.getElementById('order-form');
    const formTitle = document.getElementById('form-title');
    const searchInput = document.getElementById('search-input');
    const exportScheduledBtn = document.getElementById('export-scheduled-btn');
    const importCsvBtn = document.getElementById('import-csv');
    const exportCsvBtn = document.getElementById('export-csv-btn');
    const dashboard = document.getElementById('dashboard');
    const resetViewBtn = document.getElementById('reset-view-btn');

    // 대시보드 요소
    const sentSweetP = document.getElementById('sent-sweet-persimmon');
    const sentDaebongP = document.getElementById('sent-daebong-persimmon');
    const unsentOrdersCount = document.getElementById('unsent-orders-count');
    const unsentSweetP = document.getElementById('unsent-sweet-persimmon');
    const unsentDaebongP = document.getElementById('unsent-daebong-persimmon');
    const delayedRedCount = document.getElementById('delayed-red-count');
    const delayedRedSweet = document.getElementById('delayed-red-sweet');
    const delayedRedDaebong = document.getElementById('delayed-red-daebong');
    const redThresholdInput = document.getElementById('red-threshold');
    const redThresholdLabel = document.getElementById('red-threshold-label');

    // 상태 관리
    let orders = JSON.parse(localStorage.getItem('orders')) || [];
    let redThreshold = parseInt(localStorage.getItem('redThreshold')) || 7;
    let currentSort = { column: 'orderDate', direction: 'desc' };
    let activeFilter = 'all';

    // 데이터 저장
    const saveData = () => {
        localStorage.setItem('orders', JSON.stringify(orders));
        localStorage.setItem('redThreshold', redThreshold);
    };

    // UI 새로고침
    const refreshUI = () => {
        renderTable(getFilteredAndSortedOrders());
        updateDashboard();
        updateActiveFilterUI();
    };

    const updateActiveFilterUI = () => {
        document.querySelectorAll('.clickable-summary').forEach(item => {
            if (item.dataset.filter === activeFilter) {
                item.classList.add('active');
            } else {
                item.classList.remove('active');
            }
        });
    };

    // 대시보드 업데이트
    const updateDashboard = () => {
        let sentSweet = 0, sentDaebong = 0;
        let unsentCount = 0, unsentSweet = 0, unsentDaebong = 0;
        let redCount = 0, redSweet = 0, redDaebong = 0;
        
        const today = new Date();
        today.setHours(0, 0, 0, 0);

        orders.forEach(order => {
            const sweet = parseInt(order.sweetPersimmon) || 0;
            const daebong = parseInt(order.daebongPersimmon) || 0;

            if (order.status === '발송완료') {
                sentSweet += sweet;
                sentDaebong += daebong;
            } else {
                unsentCount++;
                unsentSweet += sweet;
                unsentDaebong += daebong;

                const orderDate = new Date(order.orderDate);
                const diffTime = today - orderDate;
                const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); // 시간 계산 버그 픽스
                
                if (diffDays > redThreshold) {
                    redCount++;
                    redSweet += sweet;
                    redDaebong += daebong;
                }
            }
        });

        sentSweetP.textContent = sentSweet;
        sentDaebongP.textContent = sentDaebong;
        unsentOrdersCount.textContent = unsentCount;
        unsentSweetP.textContent = unsentSweet;
        unsentDaebongP.textContent = unsentDaebong;

        delayedRedCount.textContent = redCount;
        delayedRedSweet.textContent = redSweet;
        delayedRedDaebong.textContent = redDaebong;
    };

    // 날짜 형식 변환
    const formatDateWithDay = (dateString) => {
        if (!dateString) return '';
        const days = ['일', '월', '화', '수', '목', '금', '토'];
        const date = new Date(dateString + 'T00:00:00');
        const dayName = days[date.getDay()];
        return `${dateString} (${dayName})`;
    };

    // 테이블 렌더링
    const renderTable = (ordersToRender) => {
        orderTableBody.innerHTML = '';
        if (ordersToRender.length === 0) {
            orderTableBody.innerHTML = '<tr><td colspan="13" style="text-align:center;">표시할 주문이 없습니다.</td></tr>';
            return;
        }

        ordersToRender.forEach(order => {
            const row = document.createElement('tr');
            if (order.status !== '발송완료') {
                const today = new Date();
                today.setHours(0, 0, 0, 0);
                const orderDate = new Date(order.orderDate);
                const diffTime = today - orderDate;
                // 시간 계산 버그 픽스
                const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

                if (diffDays > redThreshold) {
                    row.classList.add('urgency-critical');
                }
            }

            row.innerHTML = `
                <td>${formatDateWithDay(order.orderDate)}</td>
                <td>${order.senderName}</td>
                <td>${order.senderPhone}</td>
                <td>${order.senderAddress}</td>
                <td>${order.receiverName}</td>
                <td>${order.receiverPhone}</td>
                <td>${order.receiverAddress}</td>
                <td>${order.sweetPersimmon}</td>
                <td>${order.daebongPersimmon}</td>
                <td>${order.notes || ''}</td>
                <td>
                    <select class="status-select" data-id="${order.id}">
                        <option value="미발송" ${order.status === '미발송' ? 'selected' : ''}>미발송</option>
                        <option value="발송예정" ${order.status === '발송예정' ? 'selected' : ''}>발송예정</option>
                        <option value="발송완료" ${order.status === '발송완료' ? 'selected' : ''}>발송완료</option>
                    </select>
                </td>
                <td>
                    ${order.status === '발송완료' ? 
                        `<input type="date" class="shipping-date-input" data-id="${order.id}" value="${order.shippingDate || ''}">` : 
                        (formatDateWithDay(order.shippingDate) || '')
                    }
                </td>
                <td>
                    <button class="edit-btn" data-id="${order.id}">수정</button>
                    <button class="delete-btn" data-id="${order.id}">삭제</button>
                </td>
            `;
            orderTableBody.appendChild(row);
        });
        document.getElementById('row-count-display').textContent = `총 ${ordersToRender.length}건`;
    };

    // 데이터 필터링 및 정렬
    const getFilteredAndSortedOrders = () => {
        let filteredOrders = [...orders];
        const today = new Date();
        today.setHours(0, 0, 0, 0);

        // 1. 대시보드 필터 적용
        switch (activeFilter) {
            case 'sent_sweet':
                filteredOrders = filteredOrders.filter(o => o.status === '발송완료' && (parseInt(o.sweetPersimmon) || 0) > 0);
                break;
            case 'sent_daebong':
                filteredOrders = filteredOrders.filter(o => o.status === '발송완료' && (parseInt(o.daebongPersimmon) || 0) > 0);
                break;
            case 'unsent_total':
                filteredOrders = filteredOrders.filter(o => o.status !== '발송완료');
                break;
            case 'unsent_sweet':
                filteredOrders = filteredOrders.filter(o => o.status !== '발송완료' && (parseInt(o.sweetPersimmon) || 0) > 0);
                break;
            case 'unsent_daebong':
                filteredOrders = filteredOrders.filter(o => o.status !== '발송완료' && (parseInt(o.daebongPersimmon) || 0) > 0);
                break;
            case 'delayed_red':
                filteredOrders = filteredOrders.filter(o => {
                    if (o.status === '발송완료') return false;
                    const orderDate = new Date(o.orderDate);
                    const diffTime = today - orderDate;
                    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
                    return diffDays > redThreshold;
                });
                break;
        }

        // 2. 검색어 필터 적용
        const searchTerm = searchInput.value.toLowerCase();
        if (searchTerm) {
            filteredOrders = filteredOrders.filter(order => 
                Object.values(order).some(value => 
                    String(value).toLowerCase().includes(searchTerm)
                )
            );
        }

        // 3. 정렬 적용
        filteredOrders.sort((a, b) => {
            const valA = a[currentSort.column];
            const valB = b[currentSort.column];
            let comparison = 0;
            if (valA > valB) comparison = 1; else if (valA < valB) comparison = -1;
            return currentSort.direction === 'asc' ? comparison : -comparison;
        });

        return filteredOrders;
    };

    // 모달 관리
    const openModal = (orderId = null) => {
        orderForm.reset();
        if (orderId) {
            const order = orders.find(o => o.id == orderId);
            if(order){
                formTitle.textContent = '주문 수정';
                document.getElementById('order-id').value = order.id;
                document.getElementById('sender-name').value = order.senderName;
                document.getElementById('sender-phone').value = order.senderPhone;
                document.getElementById('sender-address').value = order.senderAddress;
                document.getElementById('receiver-name').value = order.receiverName;
                document.getElementById('receiver-phone').value = order.receiverPhone;
                document.getElementById('receiver-address').value = order.receiverAddress;
                document.getElementById('sweet-persimmon').value = order.sweetPersimmon;
                document.getElementById('daebong-persimmon').value = order.daebongPersimmon;
                document.getElementById('order-date').value = order.orderDate;
                document.getElementById('notes').value = order.notes || '';
            }
        } else {
            formTitle.textContent = '새 주문';
            document.getElementById('order-id').value = '';
            document.getElementById('sender-address').value = '전남 순천시 낙안면 심내길 36';
            document.getElementById('order-date').value = new Date().toISOString().split('T')[0];
        }
        modal.style.display = 'flex';
    };
    const closeModal = () => { modal.style.display = 'none'; };

    // 이벤트 리스너
    newOrderBtn.addEventListener('click', () => openModal());
    closeModalBtn.addEventListener('click', closeModal);
    window.addEventListener('click', (e) => { if (e.target === modal) closeModal(); });

    resetViewBtn.addEventListener('click', () => {
        searchInput.value = '';
        activeFilter = 'all';
        currentSort = { column: 'orderDate', direction: 'desc' };
        refreshUI();
    });

    dashboard.addEventListener('click', (e) => {
        const target = e.target.closest('.clickable-summary');
        if (target) {
            const filter = target.dataset.filter;
            if (activeFilter === filter) {
                activeFilter = 'all'; // Toggle off
            } else {
                activeFilter = filter;
            }
            refreshUI();
        }
    });

    orderForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const id = document.getElementById('order-id').value;
        const orderData = {
            senderName: document.getElementById('sender-name').value,
            senderPhone: document.getElementById('sender-phone').value,
            senderAddress: document.getElementById('sender-address').value,
            receiverName: document.getElementById('receiver-name').value,
            receiverPhone: document.getElementById('receiver-phone').value,
            receiverAddress: document.getElementById('receiver-address').value,
            sweetPersimmon: document.getElementById('sweet-persimmon').value,
            daebongPersimmon: document.getElementById('daebong-persimmon').value,
            orderDate: document.getElementById('order-date').value,
            notes: document.getElementById('notes').value
        };
        if (id) {
            const index = orders.findIndex(o => o.id == id);
            if(index !== -1) orders[index] = { ...orders[index], ...orderData };
        } else {
            orders.push({ id: Date.now(), ...orderData, status: '미발송', shippingDate: null });
        }
        saveData();
        refreshUI();
        closeModal();
    });

    orderTableBody.addEventListener('click', (e) => {
        const id = e.target.dataset.id;
        if (e.target.classList.contains('edit-btn')) openModal(parseInt(id));
        if (e.target.classList.contains('delete-btn')) {
            if (confirm('정말로 이 주문을 삭제하시겠습니까?')) {
                orders = orders.filter(o => o.id != id);
                saveData();
                refreshUI();
            }
        }
    });

    orderTableBody.addEventListener('change', (e) => {
        const id = e.target.dataset.id;
        if (e.target.classList.contains('status-select')) {
            const index = orders.findIndex(o => o.id == id);
            if(index !== -1) {
                orders[index].status = e.target.value;
                 // Check if shippingDate needs to be reset for non '발송완료' statuses
                if (e.target.value === '발송완료') {
                    if (!orders[index].shippingDate) { // Only set if not already set
                        orders[index].shippingDate = new Date().toISOString().split('T')[0];
                    }
                } else {
                    orders[index].shippingDate = null; // Clear date if not completed
                }
                saveData();
                refreshUI();
            }
        } else if (e.target.classList.contains('shipping-date-input')) {
            const index = orders.findIndex(o => o.id == id);
            if(index !== -1) {
                orders[index].shippingDate = e.target.value;
                saveData();
                updateDashboard();
            }
        }
    });

    searchInput.addEventListener('input', () => renderTable(getFilteredAndSortedOrders()));

    document.querySelectorAll('th[data-sort]').forEach(header => {
        header.addEventListener('click', () => {
            const column = header.dataset.sort;
            if (currentSort.column === column) {
                currentSort.direction = currentSort.direction === 'asc' ? 'desc' : 'asc';
            } else {
                currentSort.column = column;
                currentSort.direction = 'desc';
            }
            refreshUI();
        });
    });

    redThresholdInput.addEventListener('change', () => {
        redThreshold = parseInt(redThresholdInput.value) || 7;
        redThresholdLabel.textContent = redThreshold;
        saveData();
        refreshUI();
    });
    
    exportScheduledBtn.addEventListener('click', () => {
        const scheduledOrders = orders.filter(o => o.status === '발송예정');
        if (scheduledOrders.length === 0) {
            alert('발송예정인 주문이 없습니다.');
            return;
        }
        const headerMapping = {
            orderDate: '주문일자', senderName: '보내는 분', senderPhone: '보내는 분 연락처', senderAddress: '보내는 분 주소',
            receiverName: '받는 분', receiverPhone: '받는 분 연락처', receiverAddress: '받는 분 주소',
            sweetPersimmon: '단감(개)', daebongPersimmon: '대봉(개)', notes: '특이사항', status: '배송 상태', shippingDate: '발송일'
        };
        const dataToExport = scheduledOrders.map(order => {
            let mappedOrder = {};
            for (const key in headerMapping) {
                mappedOrder[headerMapping[key]] = order[key] || '';
            }
            return mappedOrder;
        });
        const worksheet = XLSX.utils.json_to_sheet(dataToExport);
        const workbook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(workbook, worksheet, '발송예정 목록');
        XLSX.writeFile(workbook, '발송예정_주문목록.xlsx');
    });

    exportCsvBtn.addEventListener('click', () => {
        if (orders.length === 0) {
            alert('내보낼 데이터가 없습니다.');
            return;
        }
        const keys = ['id', 'orderDate', 'senderName', 'senderPhone', 'senderAddress', 'receiverName', 'receiverPhone', 'receiverAddress', 'sweetPersimmon', 'daebongPersimmon', 'notes', 'status', 'shippingDate'];
        const headerMapping = {
            id: '주문번호', orderDate: '주문일자', senderName: '보내는 분', senderPhone: '보내는 분 연락처', senderAddress: '보내는 분 주소',
            receiverName: '받는 분', receiverPhone: '받는 분 연락처', receiverAddress: '받는 분 주소',
            sweetPersimmon: '단감(개)', daebongPersimmon: '대봉(개)', notes: '특이사항', status: '배송 상태', shippingDate: '발송일'
        };
        const header = keys.map(key => headerMapping[key]).join(',');
        const body = orders.map(order => {
            return keys.map(key => {
                let value = order[key] === null || order[key] === undefined ? '' : String(order[key]);
                if (value.includes(',')) value = `"${value}"`;
                return value;
            }).join(',');
        }).join('\n');
        const csv = `${header}\n${body}`;
        const blob = new Blob(["\uFEFF" + csv], { type: 'text/csv;charset=utf-8;' });
        const link = document.createElement('a');
        link.href = URL.createObjectURL(blob);
        link.download = '전체_주문목록.csv';
        link.click();
    });

    importCsvBtn.addEventListener('change', (e) => {
        const file = e.target.files[0];
        if (!file) return;
        const reader = new FileReader();
        reader.onload = (event) => {
            const text = event.target.result;
            const lines = text.trim().split(/\r\n|\n/);
            const headerLine = lines.shift().replace(/\uFEFF/g, '');
            const headers = headerLine.split(',');
            const csvRowRegex = /,(?=(?:[^\"]*"[^\"]*")*[^\"]*$)/;
            const importedOrders = lines.map(line => {
                if (!line) return null;
                const values = line.split(csvRowRegex).map(v => v.replace(/^"|"$/g, ''));
                if (values.length === headers.length) {
                    const order = {};
                    headers.forEach((h, i) => { order[h.trim()] = values[i].trim(); });
                    return order;
                }
                return null;
            }).filter(Boolean);
            if (importedOrders.length === 0) {
                alert('파일에서 유효한 주문 데이터를 찾을 수 없습니다.');
                return;
            }
            if (confirm('기존 데이터를 유지하고 가져온 데이터를 추가하시겠습니까? \n취소를 누르면 기존 데이터가 삭제됩니다.')) {
                const newOrders = importedOrders.map(o => {
                    const newOrder = {};
                    const headerMap = {
                        '주문번호': 'id', '주문일자': 'orderDate', '보내는 분': 'senderName', '보내는 분 연락처': 'senderPhone', '보내는 분 주소': 'senderAddress', 
                        '받는 분': 'receiverName', '받는 분 연락처': 'receiverPhone', '받는 분 주소': 'receiverAddress', '단감(개)': 'sweetPersimmon', 
                        '대봉(개)': 'daebongPersimmon', '특이사항': 'notes', '배송 상태': 'status', '발송일': 'shippingDate'
                    };
                    for(const key in o) {
                        if(headerMap[key]) {
                            newOrder[headerMap[key]] = o[key];
                        }
                    }
                    newOrder.id = parseInt(newOrder.id) || (Date.now() + Math.random());
                    return newOrder;
                });
                orders = [...orders, ...newOrders];
            } else {
                 orders = importedOrders.map(o => {
                    const newOrder = {};
                    const headerMap = {
                        '주문번호': 'id', '주문일자': 'orderDate', '보내는 분': 'senderName', '보내는 분 연락처': 'senderPhone', '보내는 분 주소': 'senderAddress', 
                        '받는 분': 'receiverName', '받는 분 연락처': 'receiverPhone', '받는 분 주소': 'receiverAddress', '단감(개)': 'sweetPersimmon', 
                        '대봉(개)': 'daebongPersimmon', '특이사항': 'notes', '배송 상태': 'status', '발송일': 'shippingDate'
                    };
                    for(const key in o) {
                        if(headerMap[key]) {
                            newOrder[headerMap[key]] = o[key];
                        }
                    }
                    newOrder.id = parseInt(newOrder.id) || (Date.now() + Math.random());
                    return newOrder;
                });
            }
            saveData();
            refreshUI();
            alert(`${importedOrders.length}개의 주문을 가져왔습니다.`);
        };
        reader.readAsText(file, 'UTF-8');
        e.target.value = '';
    });

    // 초기화
    const initialize = () => {
        redThresholdInput.value = redThreshold;
        redThresholdLabel.textContent = redThreshold;
        refreshUI();
    };

    initialize();
});
